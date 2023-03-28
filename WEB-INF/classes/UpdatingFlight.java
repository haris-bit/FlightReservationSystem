import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;


public class UpdatingFlight extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // getSession(false) : Returns the current HttpSession associated with this request, if there is no current session, returns null.
        HttpSession session = request.getSession(false);

        // add the user_record class object here
        // session.setAttribute();

        String email = request.getParameter("email");
        ResultSet rs_search = null;
        int rs = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/class_test";
            Connection con=DriverManager.getConnection(url,"root","root");
            Statement st=con.createStatement();
        
            String query1 = "select * from flightrecords where email = '"+email+"';";
            rs_search = st.executeQuery( query1 );
            out.println("<html><head><title>Entry Exists</title></head>");
            out.println("<body>");
            while (rs_search.next()) {
                
                out.println("<h1>Entry exists</h1>");
                out.println("<h3>" + email+ " exists</h3>"); 
                out.println("<h3> Passenger Name: " + rs_search.getString("passengerName") + "</h3>");
                out.println("<h3> Email: " + rs_search.getString("email") + "</h3>");
                out.println("<h3> Phone Number: " + rs_search.getString("phoneNumber") + "</h3>");
                out.println("<h3> Start Date and Time: " + rs_search.getString("startDAT") + "</h3>");
                out.println("<h3> Return Date and Time: " + rs_search.getString("returnDAT") + "</h3>");
                out.println("<h3> Country: " + rs_search.getString("country") + "</h3>");
                out.println("<h3> Airline: " + rs_search.getString("airline") + "</h3>");

                // Need to print out all the data related to a single flight
                
                // need to add some input fields so that the user can update it's respective record
                out.println("<input type = 'text' name = 'passengerName' placeholder = 'Passenger Name' />");
                // out.println("<input type = 'text' name = 'email' placeholder = 'Email' />");
                out.println("<input type = 'text' name = 'phoneNumber' placeholder = 'Phone Number' />");
                out.println("<input type = 'datetime-local' id = 'start' name = 'start' min = '2022-01-07T00:00' value = '2022-01-14T00:00' placeholder = 'Start Date and Time'/>");
                out.println("<input type = 'datetime-local' id = 'return' name = 'return' min = '2022-01-07T00:00' value = '2022-01-14T00:00' placeholder = 'Return Date & Time'/>");
                out.println("<input type= 'text' name = 'destinationCountry' placeholder = 'Destination Country: ' />");
                out.println("<input type = 'text' name = 'airline' placeholder = 'Airline: ' />");
                out.println("<input type = 'submit' name = 'submit' value = 'Update Flight' />");                

                // get the field names from this page and then use an sql query to update the table with the given input parameters


            //     <form action="AddFlight" method = "post">
            //     <!-- <div class="box"> -->
            //     <div class = "box">
            //     <h1>Flight Reservation</h1>
            //     <p>Reservation Details</p>
            //     <p>Please make sure that you fill in the name which is in your passport.</p>
            //     <input type="text" name="passengerName" placeholder = "Passenger Name: " />
            //     <br />  
            //     <input type="text" name="email" placeholder = "Email: " />
            //     <br />
            //     <input type="text" name="phoneNumber" placeholder = "Phone Number: " />
            //     <br />
            //     <!-- Returns a DOMString -->
            //     <label for = "start">Departure Date & Time:</label>
            //     <input type = "datetime-local" id = "start" name = "start" 
            //     min = "2022-01-07T00:00" value = "2022-01-14T00:00"/>
                
            //     <br />                        
            //     <!-- Returns a DOMString -->
            //     <label for = "return">Return Date & Time:</label>
            //     <input type = "datetime-local" id = "return" name = "return" 
            //     min = "2022-01-07T00:00" value = "2022-01-14T00:00"/>
                
            //     <br />                        
            //     <input type="text" name="destinationCountry" placeholder = "Destination Country: " />
            //     <br />                        
            //     <input type="text" name="airline" placeholder = "Airline: " />
            //     <br />                        
            //     <br/>                        
            //     <input type = "submit" name = "submit" value = "Reserve Flight" />
            //     <br />  
            //     </div>
            // </form>



                // this might close the server        
                // System.exit(0);  
            } 
            out.println("<button onclick = \"parent.location = 'mainPage.html'\">Go to Main Page</button>");
            out.println("</body>");
            out.println("</html>");        
            out.close(); 

        }   
         catch (Exception e) {
            System.out.println(e);     
            e.printStackTrace();
        }

    }
 }
