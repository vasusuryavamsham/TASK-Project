package com.task;

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
import javax.servlet.http.HttpSession;

import com.task.ListMovies;

public class AddMovie extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String mname = request.getParameter("mname");
		int mprice = Integer.parseInt(request.getParameter("mprice"));
		String mlang = request.getParameter("mlang");
		String mcity = request.getParameter("mcity");
		
		
try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://@localhost:3306/OMTS",
					"root","root");
			
			PreparedStatement pst=con.prepareStatement(
					"insert into movies values(?,?,?,?)");
			pst.setString(1, mname);
			pst.setInt(2, mprice);
			pst.setString(3, mlang);
			pst.setString(4, mcity);
			pst.executeUpdate();
			pst.close();
			
			
			
			pw.println("movie: "+mname+" added successfully.");
			HttpSession session = request.getSession();
			String name = session.getAttribute("name").toString();
			request.setAttribute("name", name);
			
			RequestDispatcher rd=request.getRequestDispatcher("adminhome.jsp");
			rd.include(request, response);
//			pw.println("<script>document.getElementById('result').innerHTML = '" + result + "';</script>");
			ListMovies lm = new ListMovies();
			lm.doGet(request,response,false);
			
			
			
			
		}
		catch(Exception e) {
			pw.println(e);
			pw.println("Failed to add, Try again");
			RequestDispatcher rd=request.getRequestDispatcher("adminhome.jsp");
			rd.include(request, response);
		}
		
		
	}
}
