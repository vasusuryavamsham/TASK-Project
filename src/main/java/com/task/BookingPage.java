package com.task;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookingPage extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String mname = request.getParameter("movie");
        String mprice = request.getParameter("price");
        String mlang = request.getParameter("language");
        String mcity = request.getParameter("city");

        try {
            // Forward the request to the JSP page
            request.setAttribute("movie", mname);
            request.setAttribute("price", mprice);
            request.setAttribute("language", mlang);
            request.setAttribute("city", mcity);
            RequestDispatcher rd=request.getRequestDispatcher("billing.jsp");
            rd.forward(request, response);
        } catch(Exception e) {
            pw.println(e);
            pw.println("Failed to book, Try again");
            RequestDispatcher rd=request.getRequestDispatcher("userhome.jsp");
            rd.include(request, response);
        }
    }
}
