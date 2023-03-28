<html>
<head>
<title>View Flights</title>
<link rel = "stylesheet" type = "text/css" href = "style2.css" />
</head>

<%@ page language = "java" import = "java.io.*,javax.servlet.http.*,javax.servlet.*,java.sql.*,java.util.*" %>

<body>
<h1>Information</h1>

<table border = "1" width = "400">
<tr>
<td><b>Passenger Name</b></td>
<td><b>Email</b></td>
<td><b>Phone Number</b></td>
<td><b>Start Date and Time</b></td>
<td><b>Return Date and Time</b></td>
<td><b>Country</b></td>
<td><b>Airline</b></td>
</tr>
<%

       // PrintWriter out = response.getWriter();

        // getSession(false) : Returns the current HttpSession associated with this request, if there is no current session, returns null.
       // HttpSession session = request.getSession(false);

        // add the user_record class object here
        // session.setAttribute();        

        String email1 = request.getParameter("email");
        ResultSet rs_search = null;
        int rs = 0;
        int deleting_rs = 0;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/class_test";
            Connection con=DriverManager.getConnection(url,"root","root");
            Statement st=con.createStatement();

            String passengerName = "";
            String email = "";
            String phoneNumber = "";
            String sDAT = "";
            String rDAT = "";
            String country = "";
            String airline = "";
%>
            <td><b>passengerName</b></td>
            <td><b>email</b></td>
            <td><b>phoneNumber</b></td>
            <td><b>startDAT</b></td>
            <td><b>returnDAT</b></td>
            <td><b>country</b></td>
            <td><b>airline</b></td>
        <%
            String query1 = "select * from flightrecords";
            rs_search = st.executeQuery( query1 );
            
            while(rs_search.next()) {
                passengerName = rs_search.getString("passengerName");
                email = rs_search.getString("email");
                phoneNumber = rs_search.getString("phoneNumber");
                sDAT = rs_search.getString("startDAT");
                rDAT = rs_search.getString("returnDAT");
                country = rs_search.getString("country");
                airline = rs_search.getString("airline");

               %>
               
               <tr>
               <td><% out.println(passengerName); %></td>
               <td><%= email %></td>
               <td><%= phoneNumber %></td>
               <td><%= sDAT %></td>
               <td><%= rDAT %></td>
               <td><%= country %></td>
               <td><%= airline %></td>
               </tr>
               <%
               }
               %>
               <%
               st.close();
               con.close(); }

               catch(Exception e){}
               %>
               <button class = "button" name = "" value = "logout" onclick = "parent.location = 'mainPage.html'">Main Page</button>
</table>
</body>
</html>
