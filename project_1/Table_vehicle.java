package project_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by ghoshri on 7/22/2017.
 */
@WebServlet(name = "Table_vehicle")
public class Table_vehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String a1 = null;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cdk", "root", "CDKcdk11");
            PreparedStatement stmt=con.prepareStatement("select * from vehicle");
            ResultSet rs=stmt.executeQuery();
            out.print("<style>\n" +
                    "table, th, td {\n" +
                    "    border: 1px solid black;\n" +
                    "}\n" +
                    "table {\n" +
                    "    border-spacing: 15px;\n" +
                    "}"+"</style>");
            out.print("<table style=\"width:100%\">\n" +
                    "  <tr>\n" +
                    "    <th>vehicle id</th>\n" +
                    "    <th>units</th> \n" +
                    "    <th>price</th>\n" +
                    "    <th>model</th>\n" +
                    "  </tr>");
            while (rs.next()){
                out.print("<tr>\n" +
                        "    <td>"+rs.getString(1) +"</td>\n" +
                        "    <td>"+ rs.getInt(2)+"</td> \n" +
                        "    <td>"+rs.getDouble(3)+"</td>\n" +
                        "    <td>"+rs.getString(4) +"</td>\n" +
                        "  </tr>");

            }
            out.print("<a href='http://localhost:8080/project_1.Servlet_admin'>to go to main menu</a>");
        }catch(Exception ex) {
    }
    }
}
