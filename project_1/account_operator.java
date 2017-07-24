package project_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

/**
 * Created by ghoshri on 7/18/2017.
 */
@WebServlet(name = "account_operator")
public class account_operator extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String n = request.getParameter("username");
        String a = request.getParameter("address");
        HttpSession session=request.getSession(false);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            int i = 1;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cdk", "root", "CDKcdk11");
            PreparedStatement stmt = con.prepareStatement("insert into members values(?,?,?)");
            stmt.setString(1, n);
            stmt.setString(2, a);
            stmt.setInt(3, i);
            int count = stmt.executeUpdate();
             stmt= con.prepareStatement("insert into log values(?,?,?,?)");
            stmt.setInt(1,2);
            stmt.setString(2,(String)session.getAttribute("uname"));
            stmt.setString(3,"added a new operator");
            Date d2 = new Date(System.currentTimeMillis());
//            SimpleDateFormat st=new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss");
            stmt.setString(4,d2.toString());
            stmt.executeUpdate();
            out.print(n + " has been registered<br>");
            out.print("<br><a href='http://localhost:8080/project_1.Servlet_admin'>to go to main menu</a>");

            out.print("<br><form action='http://localhost:8080/project_1.ServletLogout' method='get'><input type='submit' value='logout'></form>");


        } catch (Exception c) {
        }
    }
}
