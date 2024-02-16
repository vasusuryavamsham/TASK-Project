package com.task;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String uname = request.getParameter("username");
		String pwd1 = request.getParameter("password1");
		String pwd2 = request.getParameter("password2");
		String type = request.getParameter("type");
		if(!(pwd1.equals(pwd2)) || uname.equals(" ")) {
			pw.println("check password and username");
			RequestDispatcher rd=request.getRequestDispatcher("registration.html");
			rd.include(request, response); 
		}
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://@localhost:3306/OMTS",
					"root","root");
			
			PreparedStatement pst=con.prepareStatement(
					"insert into "+type+" values(?,?)");
			pst.setString(1, uname);
			pst.setString(2, pwd1);
			pst.executeUpdate();
			pst.close();
			pw.println("Registered Successfully ");
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		catch(Exception e) {
			pw.println(e);
			pw.println("failed to register, select other username");
			RequestDispatcher rd=request.getRequestDispatcher("register.html");
			rd.include(request, response);
		}
		
		
		
		
	}

}
