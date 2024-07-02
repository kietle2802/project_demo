<%@page import="java.util.ArrayList"%>
<%@page import="model.Magazine"%>
<%@page import="model.MagazinesDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <h1>MAGAZINE!</h1>
    <%
    Integer hitsCount = (Integer) application.getAttribute("hitCounter");
    if (hitsCount == null || hitsCount == 0) {
        /* First visit */
        out.print("Welcome to my website! ");
        hitsCount = 1;
    } else {
        /* return visit */
        out.print("Welcome back to my website! ");
        hitsCount += 1;
    }
    application.setAttribute("hitCounter", hitsCount);
    out.println("Total number of visits: " + hitsCount);
    %>
    <table border="1">
        <br><br>
        <tr>
            <td>ID</td>
            <td>Title</td>
            <td>Publisher</td>
            <td>Price</td>
        </tr>
        <%        
        MagazinesDAO ls = new MagazinesDAO();
        ArrayList<Magazine> list = ls.getAll("");
        if(list != null) {
        for (Magazine m : list) {
            out.print("<tr>"
                    + "<td>" + m.getID() + "</td>"
                    + "<td>" + m.getTitle() + "</td>"
                    + "<td>" + m.getPublisher() + "</td>"
                    + "<td>" + m.getPrice() + "</td>"
                    + "</tr>"
            );
        }
            }
           
           
        %>
    </table>
    <form action="Magazine.jsp">
        <br><br>
        <input type="submit" value="Add new Magazine">
    </form>
</body>
</html>
