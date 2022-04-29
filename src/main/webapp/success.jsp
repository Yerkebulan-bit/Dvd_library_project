<%@ page import="com.dvd.model.DVDItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <% DVDItem item = (DVDItem) session.getAttribute("dvdItem"); %>
    <h1>Success</h1>
    <p>A new DVD: "<%=item.getTitle()%>" added successfully</p>
    <a href="index.jsp">Home</a>
</body>
</html>
