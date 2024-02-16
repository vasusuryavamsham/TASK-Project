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

public class ListMovies extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response,boolean user) throws IOException, ServletException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection(
						"jdbc:mysql://@localhost:3306/OMTS",
						"root","root");
				PreparedStatement pst=con.prepareStatement(
						"select * from movies");
				ResultSet rs = pst.executeQuery();
				String result = "";
				while(rs.next()) {
					 result+=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"<br>";
				}	
				rs.close();
				pst.close();
//				RequestDispatcher rd = request.getRequestDispatcher("adminhome.html");
//				rd.include(request, response);
//				pw.println("<script>document.getElementById('result').innerHTML = '" + result + "';</script>");
				
				if(user) {
					ptable( pw, result,true);
				}
				else {
					ptable( pw, result,false);
				}




		
			}
			catch(Exception e) {
				pw.println(e);
				pw.println("operation failed, try again.");
				RequestDispatcher rd=request.getRequestDispatcher("adminhome.html");
				rd.include(request, response);
	         }
	}
	public void ptable(PrintWriter pw, String result,boolean sb) {
		String id="result";
		String fc="bclicked('+ movie[0]+' , '+movie[2]+')";
		pw.println("<script>");
		pw.println("var result = '" + result + "';");
		pw.println("var movies = result.split('<br>');");
		pw.println("var tableHtml = '<center><table style=\"border-collapse: collapse;width:80%; text-align:center;\">';");
		pw.println("tableHtml += '<tr><th style=\"border: 1px solid black; padding: 8px;width:20%;\">Movie</th><th style=\"border: 1px solid black; padding: 8px;\">Price</th><th style=\\\"border: 1px solid black; padding: 8px;\\\">Language</th><th style=\\\"border: 1px solid black; padding: 8px;\\\">City</th>';"); // Header row
		if(sb) {
			pw.println("tableHtml += '<th style=\\\"border: 1px solid black; padding: 8px;\\\">Book now</th>';");
		}
		pw.println("tableHtml += '</tr>'");
		pw.println("for (var i = 0; i < movies.length; i++) {");
		pw.println("    var movie = movies[i].trim().split(' ');"); // Trim to remove leading and trailing whitespace
		pw.println("    if (movie.length === 4 && movie[0] !== '' && movie[1] !== '') {"); // Ensure each line has both movie name and price
		pw.println("        tableHtml += '<tr>';");
		pw.println("        tableHtml += '<td style=\"border: 1px solid black; padding: 8px;\">' + movie[0] + '</td>';"); // Movie cell
		pw.println("        tableHtml += '<td style=\"border: 1px solid black; padding: 8px;\">' + movie[1] + '</td>';"); // Price cell
		pw.println("        tableHtml += '<td style=\"border: 1px solid black; padding: 8px;\">' + movie[2] + '</td>';"); // language cell
		pw.println("        tableHtml += '<td style=\"border: 1px solid black; padding: 8px;\">' + movie[3] + '</td>';"); // location cell
		if(sb) {
		    pw.println("        tableHtml += '<td style=\"border: 1px solid black; padding: 8px;\"><button onclick=\""+fc +"\">Click Here</button></td>';"); // Book Now button cell
		}

		pw.println("        tableHtml += '</tr>';");
		pw.println("    }");
		pw.println("}");
		pw.println("tableHtml += '</table></center>';");
		pw.println("document.getElementById('"+id+"').innerHTML = tableHtml;");
//		pw.println("function bclicked(movieName, language) {document.getElementById('movieName').value = movieName; document.getElementById('language').value = language; document.getElementById('bookNowForm').submit();");
		pw.println("</script>");
	}
}

