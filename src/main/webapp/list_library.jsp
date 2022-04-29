
<%@ page import="com.dvd.model.DVDLibrary" %>
<%@ page import="com.dvd.model.DVDItem" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>

</head>
<body>
    <%
        DVDLibrary dvdLibrary = (DVDLibrary) session.getAttribute("library");
        List<DVDItem> dvdItems = dvdLibrary.getDVDCollection();

        String showTitle = (String) session.getAttribute("showTitle");
        String showYear = (String) session.getAttribute("showYear");
        String showGenre = (String) session.getAttribute("showGenre");
    %>
    <h1> DVD list </h1>
    <table border="1px">
        <tr>
            <% if(showTitle != null && showTitle.equals("true")) {%>
            <td>Title</td>
            <%}%>
            <% if(showYear != null && showYear.equals("true")) {%>
            <td>Year</td>
            <%}%>
            <% if(showGenre != null && showGenre.equals("true")) {%>
            <td>Genre</td>
            <%}%>
        </tr>

        <%for(DVDItem dvdItem:dvdItems) { %>
        <tr>

            <% if(showTitle != null && showTitle.equals("true")) {%>
                <td><p>  <%=dvdItem.getTitle()%> </p></td>
            <%}%>

            <% if(showYear != null && showYear.equals("true")) {%>
                <td> <%=dvdItem.getYear()%> </td>
            <%}%>

            <% if(showGenre != null && showGenre.equals("true")) {%>
                <td> <%=dvdItem.getGenre()%> </td>
            <%}%>

        </tr>
        <%}%>

    </table>

</body>
</html>
