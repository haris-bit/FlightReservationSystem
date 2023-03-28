import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;


public class Something extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // getSession() : Returns the current session associated with this request, or if the request does not have a session, creates one.
        HttpSession session = request.getSession();

        // add the user_record class object here
        // session.setAttribute();

        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        // String email = request.getParameter("email");
        // String phoneNo = request.getParameter("phoneNumber");
        ResultSet adminRs = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/class_test";
            Connection con=DriverManager.getConnection(url,"root","root");
            Statement st=con.createStatement();
        

            // Checking id the user name contains admin anywhere
            // String adminQuery = "select * from user_entries where username like '"+"%admin%"+"'";
                        
            // adminRs = st.executeQuery( adminQuery );
            // if the database contains any admin entry, then print it out
            // if (adminRs.next()) {
            //     String name = rs.getString("username");
            //     String pwd = rs.getString("password");
            //     out.println("<html><head><title>Welcome</title></head>");
            //     out.println("<body>");
            //     out.println("<h1>Welcome Screen (Admin) </h1>");
            //     out.println("<h4>" + "Welcome Back, " + userName + "</h4>");
            //     out.println("<button onclick = \"parent.location = 'adminMainPage.html'\">Go to Admin Main Page</button>");
            //     out.println("</body>");
            //     out.println("</html>");               
            //     return; 
            // }


            // Checking if the username and password exist in the database
            String query = "select * from user_entries where username = '"+userName+"' and password = '"+passWord+"'; ";
            rs = st.executeQuery( query );
    
            // out.println(rs);
            
            if (rs.next()) {     
                // System.out.println("Record");
                // System.out.println(rs.getString("username"));
                // System.out.println(rs.getString("password"));
                // System.out.println(rs.getString("email"));
                // System.out.println(rs.getString("phoneNo"));
                // System.out.println();
                String name = rs.getString("username");
                String pwd = rs.getString("password");
    
                out.println("<html><head><title>Welcome</title></head>");
                out.println("<body>");
                out.println("<h1>Welcome Screen</h1>");
                out.println("<h4>" + "Welcome Back, " + userName + "</h4>");
                out.println("<button onclick = \"parent.location = 'mainPage.html'\">Go to Main Page</button>");
                out.println("</body>");
                out.println("</html>");
    
             }
    
             else{
                out.println("<html><head><title>Practice</title></head>");
                out.println("<body>");
                out.println("<h1>Practice File</h1>");
                out.println("<h4>Entry not found</h4>");
                out.println("<button onclick = \"parent.location = 'Login.html'\">Go to Login Page</button>");
                out.println("</body>");
                out.println("</html>");
                }
    

        } 
        
        catch (Exception e) {
            System.out.println(e);     
            e.printStackTrace();
        }

       
        out.close(); 

    }
 }
