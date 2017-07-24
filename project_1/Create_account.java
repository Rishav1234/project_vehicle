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
import java.sql.Statement;

/**
 * Created by ghoshri on 7/18/2017.
 */
@WebServlet(name = "Create_account")
public class Create_account extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String n=request.getParameter("username");
        String a=request.getParameter("address");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            int i=0;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cdk", "root", "CDKcdk11");
            PreparedStatement stmt=con.prepareStatement("insert into members values(?,?,?)");
            stmt.setString(1,n);
            stmt.setString(2,a);
            stmt.setInt(3,i);
            int count = stmt.executeUpdate();
            con.close();
            out.print(n+" has been registered");



        }catch (Exception c){

        }

        }
}
