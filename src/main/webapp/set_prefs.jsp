
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Set Display Preferences</h1>
<form name="show" action = "setPreference.action" method="post">
    <div><input type="checkbox" id="showTitle" name="showTitle" value="showTitle">
    <label for="showTitle">Title</label>
    </div>
    <div>
    <input type="checkbox" id="showYear" name="showYear" value="showYear">
    <label for="showYear">Year</label>
    </div>
    <input type="checkbox" id="showGenre" name="showGenre" value="showGenre">
    <label for="showGenre">Genre</label>
    <div>
    <input type="submit" value="Set Preferences">
    </div>
</form>
</body>
</html>
