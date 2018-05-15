<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="assemblage.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> User Logged In Successfully </title>
</head>
<body>

	<% UserBean currentUser = (UserBean) session.getAttribute("currentSessionUser"); %>
	Welcome <%= currentUser.getFirstName() + " " + currentUser.getlastName() %>

</body>
</html>