package project_1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by ghoshri on 7/18/2017.
 */
@WebServlet(name = "login_servlet")
public class login_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String n=request.getParameter("uname");
        String a1=request.getParameter("addre");
        try{

            Class.forName("com.mysql.jdbc.Driver");
            String a=null;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cdk", "root", "CDKcdk11");
            PreparedStatement stmt=con.prepareStatement("select name,privilege FROM members where address=?");
            stmt.setString(1,a1);
            ResultSet rs=stmt.executeQuery();

//                out.print("<form action='http://localhost:8080/project_1.menu_servlet' method='get'><input type='submit' value='Next'></form>");
            if(rs.next()){
                if(rs.getString(1).equals(n)){
                    HttpSession session=request.getSession(true);
                    session.setAttribute("uname",n);
                    session.setAttribute("uaddress",a1);
                    session.setAttribute("privilege",rs.getInt(2));
                    int privilege=rs.getInt(2);
                    if(privilege==1){
                    RequestDispatcher rd=request.getRequestDispatcher("/project_1.menu_servlet");
                    rd.forward(request,response);
                    }
                    else if(privilege==2){
                        RequestDispatcher rd=request.getRequestDispatcher("/project_1.Servlet_admin");
                        rd.forward(request,response);
                    }else{
                        RequestDispatcher rd=request.getRequestDispatcher("/project_1.Servlet_user");
                        rd.forward(request,response);
                    }
                }
            } else{
                out.print("Sorry UserName or address Error!");
                RequestDispatcher rd=request.getRequestDispatcher("/login_project.html");
                rd.include(request, response);
            }





        }catch (Exception et){

        }
    }
}
