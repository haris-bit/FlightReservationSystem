import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;


public class AddFlight extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // getSession(false) : Returns the current HttpSession associated with this request, if there is no current session, returns null.
        HttpSession session = request.getSession(false);

        // add the user_record class object here
        // session.setAttribute();


        String passengerName = request.getParameter("passengerName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String startDAT = request.getParameter("start");
        String returnDAT = request.getParameter("return");
        String country = request.getParameter("destinationCountry");
        String airline = request.getParameter("airline");
        ResultSet rs_search = null;
        int rs = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/class_test";
            Connection con=DriverManager.getConnection(url,"root","root");
            Statement st=con.createStatement();
        
            String query1 = "select * from flightrecords where email = '"+email+"';";
            rs_search = st.executeQuery( query1 );
            if (rs_search.next()) {
                out.println("<html><head><title>Entry Exists</title></head>");
                out.println("<body>");
                out.println("<h1>Entry exists</h1>");
                out.println("<h4>" + email+ " already exists</h4>");
                out.println("</body>");
                out.println("</html>");
                // this might close the server        
                // System.exit(0);  
            } else {

            // then this query
            String query2 ="insert into flightrecords(passengerName,email,phoneNumber, startDAT, returnDAT, country, airline) values('"+passengerName+"', '"+email+"', '"+phoneNumber+"', '"+startDAT+"', '"+returnDAT+"', '"+country+"', '"+airline+"')";
    
            rs = st.executeUpdate( query2 );
        
            System.out.println(rs);  
            
            if(rs > 0){
                out.println("<html><head><title>Practice</title></head>");
                out.println("<body>");
                out.println("<h1>Practice File</h1>");
                out.println("<h4>" + passengerName + " is successfully registered</h4>");
                out.println("<button onclick = \"parent.location = 'mainPage.html'\">Go to Main Page</button>");
                out.println("</body>");
                out.println("</html>");
              }
             
             else{
                out.println("<html><head><title>Practice</title></head>");
                out.println("<body>");
                out.println("<h1>Practice File</h1>");
                out.println("<button onclick = \"parent.location = 'mainPage.html'\">Go to Main Page</button>");
                out.println("<h4>An error occured</h4>");
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
