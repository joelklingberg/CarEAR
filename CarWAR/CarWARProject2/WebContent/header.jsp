<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="assemblage.UserBean" %>
<html>
    <head>
        <!-- Bootstrap CSS CDN: -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <!-- Custom styling -->
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
    <% UserBean currentUser = (UserBean) session.getAttribute("currentSessionUser"); %>
        <header>
            <!-- Fixed navbar -->
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">Car Go</a>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="cars?action=showCars">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="about.jsp">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Test</a>
                        </li>
                        <!-- Code for disabled navbar link:
                        <li class="nav-item">
                            <a class="nav-link disabled" href="#">Disabled</a>
                        </li>
                        -->
                    </ul>
                </div>
                <ul class="nav navbar-nav navbar-right">
                	<% if (currentUser == null) { %>
                		<li><a href="login.jsp">Login</a></li>
                	<% } else { %>
						<li class="nav-item dropdown">
				        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				          Logged in as <%= currentUser.getUsername() %>
				        </a>
				        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
				          <a class="dropdown-item" href="users?action=showProfile&username=<%= currentUser.getUsername() %>">View profile</a>
				          <a class="dropdown-item" href="registerCar.jsp">Register car</a>
				          <div class="dropdown-divider"></div>
				          <a class="dropdown-item" href="users?action=logoutUser">Logout</a>
				        </div>
				      </li>
                	<% } %>
                </ul>
            </nav>
            <!-- End Fixed navbar -->
        </header>