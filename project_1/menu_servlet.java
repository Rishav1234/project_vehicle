package project_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ghoshri on 7/18/2017.
 */
@WebServlet(name = "menu_servlet")
public class menu_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//
        HttpSession ssn=request.getSession(false);

        //retrieve the attribute uname, from the ssn
        String n=(String)ssn.getAttribute("uname");
        String a=(String)ssn.getAttribute("uaddress");
        out.print("Hello Operator : "+n+"<br>");
        out.print("select any option below to choose your action");
//        out.print("<form action=\"http://localhost:8080/main.html\" method=\"get\"><input type=\"submit\"  value=\"button\" name=\"\"></form>");

        out.print("<ol type=\"1\">" +
                "<li><a href=\"http://localhost:8080/spare_part.html\">\n" +
                "    click on the button to add spare parts</a></li><br>\n" +
                "<li><a href=\"http://localhost:8080/spare_part_sell.html\">\n" +
                "    click on the button to sell spare parts</a></li><br>\n" +
                "<li><a href=\"http://localhost:8080/vehicle.html\">\n" +
                "    click on the button to add vehicles</a></li><br>\n" +
                "<li><a href=\"http://localhost:8080/vehicle_sell.html\">\n" +
                "    click on the button to sell vehicles</a></li>");
        out.print("<br><br>to logout press<form action='http://localhost:8080/project_1.ServletLogout' method='get'><input type='submit' value='logout'></form>");
    }
}
