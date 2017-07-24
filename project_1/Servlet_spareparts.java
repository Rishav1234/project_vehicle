package project_1;

import org.omg.PortableInterceptor.INACTIVE;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by ghoshri on 7/18/2017.
 */
@WebServlet(name = "Servlet_spareparts")
public class Servlet_spareparts extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String spare=request.getParameter("spr");
        String  unit= request.getParameter("unit");
        Integer unit1=Integer.parseInt(unit);
        HttpSession session=request.getSession(false);

        try{
            Class.forName("com.mysql.jdbc.Driver");
            String a1=null;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cdk", "root", "CDKcdk11");
            PreparedStatement stmt=con.prepareStatement("select units FROM spare where spare_id=?");
            stmt.setString(1,spare);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){

               Integer t= rs.getInt(1)+unit1;
//               out.print(t);
                PreparedStatement stm=con.prepareStatement("update spare set units=? where spare_id=?");
                stm.setInt(1,t);
                stm.setString(2,spare);
                stm.executeUpdate();
                out.print("your request has been updated<br>");
                PreparedStatement stmt1 = con.prepareStatement("insert into log values(?,?,?,?)");
                stmt1.setInt(1,1);
                stmt1.setString(2,(String)session.getAttribute("uname"));
                stmt1.setString(3,"add sparepart");
                Date dt=new Date(System.currentTimeMillis());
//                SimpleDateFormat st=new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
                stmt1.setString(4,dt.toString());
                stmt1.executeUpdate();

//                Integer  pr=(Integer) session.getAttribute("privilege");

                out.print("<a href=\"http://localhost:8080/project_1.menu_servlet\">to go to main menu</a>");

                out.print("<br>to log out press:");
                out.print("<form action=\"http://localhost:8080/project_1.ServletLogout\" method=\"get\"><input type=\"submit\" value=\"logout\"></form>");

            }
        }catch (Exception e){

        }
    }
}
