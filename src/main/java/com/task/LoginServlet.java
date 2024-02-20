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
public class LoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String uname=request.getParameter("username");
		String pwd=request.getParameter("password");
		String type = request.getParameter("type");
		String cuname ="";
		String cpwd = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://@localhost:3306/OMTS",
					"root","root");
			PreparedStatement pst=con.prepareStatement(
					"select * from "+type+" where name = '"+uname+"'");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				cuname = rs.getString(1);
				cpwd = rs.getString(2);
			}
			pst.close();
			if(cuname.equals(uname)&& cpwd.equals(pwd)) {
				
				request.setAttribute("name", uname);
				request.setAttribute("initial", uname.charAt(0));
				HttpSession session = request.getSession();
				session.setAttribute("initial", uname.charAt(0));
				session.setAttribute("name", uname);
				pw.println("<p style='color:red'>Login Success : "+uname+"<p>");
				
				   
				if(type.equals("admins")) {
					
					String name = session.getAttribute("name").toString();
					request.setAttribute("name", name);
					RequestDispatcher rd=request.getRequestDispatcher("adminhome.jsp");
					rd.include(request, response); 					
//					RequestDispatcher rd = request.getRequestDispatcher("adminhome.html");
//					pw.println("<script>document.getElementById('result').innerHTML = '" + result + "';</script>");
					ListMovies lm = new ListMovies();
					
					lm.doGet(request,response,false);
					
					
				}
				else {
					RequestDispatcher rd=request.getRequestDispatcher("userhome.jsp");
					rd.include(request, response); 
					ListMovies lm = new ListMovies();
					lm.doGet(request,response,true);
				}
				  
			}
			else {
				pw.println("Login failed & Please check your credentials");
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}

		}
		catch(Exception e) {
			pw.println(e);
			pw.println("Login is failed, try again");
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		
		
	}


}
