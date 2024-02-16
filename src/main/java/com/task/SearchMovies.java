package com.task;

import com.task.ListMovies;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchMovies extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String mname=request.getParameter("searchmovie");
		String col=request.getParameter("stype");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://@localhost:3306/OMTS",
					"root","root");
			String st ="";
			switch(col) {
			case "m": st = "select * from movies where name = '"+mname+"'";
					break;
			case "l":st = "select * from movies where language = '"+mname+"'";
					break;
			case "c":st = "select * from movies where city = '"+mname+"'";
					break;
			default:st = "select * from movies";
			
			}
			PreparedStatement pst=con.prepareStatement(st);
			ResultSet rs = pst.executeQuery();
			String result = "";
			while(rs.next()) {
				 result+=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"<br>";
			}	
			rs.close();
			pst.close();
			RequestDispatcher rd=request.getRequestDispatcher("userhome.html");
			rd.include(request, response); 
			ListMovies ob = new ListMovies();
			ob.ptable(pw, result,true);
			
			
			
		}
		catch(Exception e) {
			pw.println(e);
			pw.println("Login is failed, try again");
			RequestDispatcher rd=request.getRequestDispatcher("userhome.html");
			rd.include(request, response);
		}
		
	
}
}
