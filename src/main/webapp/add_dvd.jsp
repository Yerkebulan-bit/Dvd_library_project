<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String paramTitle = request.getParameter("title");
        String paramYear = request.getParameter("year");
        if (paramYear == null){paramYear = "";}
        if (paramTitle == null){paramTitle = "";}
    %>
    <h1>Add new DVD</h1>
    <form action="DVDAction.action" method="post">
        <label for="year">Year:</label>
        <input type="text" name="year" value="<%=paramYear%>" id="year"> <br><br>
        <label for="genre">Genre:</label>
        <select id="genre" name="genre">
            <option value="unknown">Select...</option>
            <option>Action</option>
            <option>Fantasy</option>
            <option>Comedy</option>
            <option>Sci-fi</option>
        </select><br><br>
        <label for="title">Title:</label>
        <input type="text" name="title" value="<%=paramTitle%>" id="title"> <br><br>
        <input type="submit" value="Add DVD">
    </form>

    <%ArrayList<String> errorMsgs = (ArrayList<String>) request.getAttribute("errorMsgs");%>
    <%if(errorMsgs != null && !errorMsgs.isEmpty()){
        for(String errorMsg:errorMsgs){%>
            <p><%=errorMsg%></p>
        <%}
    }%>
</body>
</html>
