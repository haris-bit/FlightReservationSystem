import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;


public class Attempt extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        String email = request.getParameter("email");
        String phoneNo = request.getParameter("phoneNumber");
        ResultSet rs_search = null;
        int rs = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/class_test";
            Connection con=DriverManager.getConnection(url,"root","root");
            Statement st=con.createStatement();
        
            // Scanner inp = new Scanner(System.in);
            // System.out.print("Please enter First Name: ");
            // String new_name = inp.nextLine();
            // System.out.print("Please enter Surname: ");
            // String new_surname = inp.nextLine();
        
            // table name (entries)
            // first this query
            String query1 = "select * from user_entries where username = '"+userName+"' and password = '"+passWord+"';";
            rs_search = st.executeQuery( query1 );
            if (rs_search.next()) {
                out.println("<html><head><title>Entry Exists</title></head>");
                out.println("<body>");
                out.println("<h1>Entry exists</h1>");
                out.println("<h4>" + userName + " already exists</h4>");
                out.println("<button onclick = \"parent.location = 'Login.html'\">Go to Login Page</button>");
                out.println("</body>");
                out.println("</html>");
                // this might close the server        
                // System.exit(0);  
            } else {

            // then this query
            String query2 ="insert into user_entries(username,password,email, phoneNo) values('"+userName+"', '"+passWord+"', '"+email+"', '"+phoneNo+"')";
    
            rs = st.executeUpdate( query2 );
        
            System.out.println(rs);  
            
            if(rs > 0){
                out.println("<html><head><title>Practice</title></head>");
                out.println("<body>");
                out.println("<h1>Practice File</h1>");
                out.println("<h4>" + userName + " is successfully registered</h4>");
                out.println("<button onclick = \"parent.location = 'Login.html'\">Go to Login Page</button>");                
                out.println("</body>");
                out.println("</html>");
              }
             
             else{
                out.println("<html><head><title>Practice</title></head>");
                out.println("<body>");
                out.println("<h1>Practice File</h1>");
                out.println("<h4>An error occured</h4>");
                out.println("<button onclick = \"parent.location = 'SignUp.html'\">Go to Login Page</button>");                
                out.println("</body>");
                out.println("</html>");
                 }
    
            out.close(); 

        } // end else block
            
        } catch (Exception e) {
            System.out.println(e);     
            e.printStackTrace();
        }

       


    }
 }
