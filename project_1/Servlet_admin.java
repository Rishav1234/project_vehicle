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
@WebServlet(name = "Servlet_admin")
public class Servlet_admin extends HttpServlet {
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
        out.print("Hello admin : "+n+"<br>");
        out.print("select any option below to choose your action");
//        out.print("<form action=\"http://localhost:8080/main.html\" method=\"get\"><input type=\"submit\"  value=\"button\" name=\"\"></form>");

        out.print("<ol type=\"1\">" +
                "<li><a href=\"http://localhost:8080/admin_operator.html\">\n" +
                "    click on the button to add operator</a></li><br>\n" +
                "<li><a href=\"http://localhost:8080/add_to_spareinventory.html\">\n" +
                "    click on the button to add spare parts inventory</a></li><br>\n" +
                "<li><a href=\"http://localhost:8080/remove_spare_inventory.html\">\n" +
                "    click on the button to remove spare parts inventory</a></li><br>\n" +
                "<li><a href=\"http://localhost:8080/add.html\">\n" +
                "    click on the button to add vehicle inventory</a></li><br>\n" +
                "<li><a href=\"http://localhost:8080/add_remove_vehicle.html\">\n" +
                "    click on the button to remove vehicle inventory</a></li><br>\n"+
                "<li><a href=\"http://localhost:8080/project_1.Table_vehicle\">\n" +
                        "    click on the button to see vehicle inventory</a></li><br>\n"+
                "<li><a href=\"http://localhost:8080/project_1.Spare_table\">\n" +
                "    click on the button to see spare inventory</a></li><br>\n"+
                "<li><a href=\"http://localhost:8080/project_1.Logs\">\n" +
                "    click on the button to see Logs</a></li><br>\n");


        out.print("press to logout<br><form action='http://localhost:8080/project_1.ServletLogout' method='get'><input type='submit' value='logout'></form>");

    }
}
