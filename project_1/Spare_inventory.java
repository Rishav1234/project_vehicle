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
 * Created by ghoshri on 7/21/2017.
 */
@WebServlet(name = "Spare_inventory")
public class Spare_inventory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String a1 = null;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cdk", "root", "CDKcdk11");
            String ty = request.getParameter("checkbox");
            HttpSession session=request.getSession(false);
            if (ty.equals("add")) {
                String  spare_id=request.getParameter("spare_id");
                String  units=request.getParameter("units");
                Integer units1=Integer.parseInt(units);
                String  price=request.getParameter("price");
                Integer price1=Integer.parseInt(price);
                String  vehicle_id=request.getParameter("vehicle_id");

//                out.print("<a href=\"http://localhost:8080/add.html\">input values</a>");
                PreparedStatement stmt=con.prepareStatement("insert into spare VALUES (?,?,?,?)");
                stmt.setString(1,spare_id);
                stmt.setInt(2,units1);
                stmt.setInt(3,price1);
                stmt.setString(4,vehicle_id);
//                ResultSet rs=stmt.executeQuery();
                stmt.executeUpdate();
                out.print("your request has been updated<br>");
                PreparedStatement stmt1 = con.prepareStatement("insert into log values(?,?,?,?)");
                stmt1.setInt(1,2);
                stmt1.setString(2,(String)session.getAttribute("uname"));
                stmt1.setString(3,"add sparepart");
                Date dt=new Date(System.currentTimeMillis());
                SimpleDateFormat st=new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
                stmt1.setString(4,dt.toString());
                stmt1.executeUpdate();
                out.print("<a href='http://localhost:8080/project_1.Servlet_admin'>to go to main menu</a>");
                out.print("<br>to log out press:");
                out.print("<form action='http://localhost:8080/Logout' method='get'><input type='submit' value='logout'></form>");

            } else {
                String  spare_id=request.getParameter("spare_id");
                PreparedStatement stmt=con.prepareStatement("delete from spare where spare_id=?");
                stmt.setString(1,spare_id);
                stmt.executeUpdate();
                out.print("your request has been updated<br>");
                PreparedStatement stmt1 = con.prepareStatement("insert into log values(?,?,?,?)");
                stmt1.setInt(1,2);
                stmt1.setString(2,(String)session.getAttribute("uname"));
                stmt1.setString(3,"added new sparepart to the inventory");
                Date dt=new Date(System.currentTimeMillis());
                SimpleDateFormat st=new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
                stmt1.setString(4,dt.toString());
                stmt1.executeUpdate();

                out.print("<a href='http://localhost:8080/project_1.Servlet_admin'>to go to main menu</a>");
                out.print("<br>to log out press:");
                out.print("<form action='http://localhost:8080/project_1.ServletLogout' method='get'><input type='submit' value='logout'></form>");

            }
        }catch (Exception ey){

        }
    }
}
