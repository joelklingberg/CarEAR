<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Main content -->
<main>

	<div class="container">
		<div class="row align-items-center justify-content-center">
	        <div class="col-md-4 col-md-offset-3">
	        <form action="users?action=loginUser" method="post" class="form-signin">
	      	<h1 class="h3 mb-3 font-weight-normal text-center">Please sign in</h1>
	      	<input type="text" name="username" class="form-control mb-2" placeholder="Username" required autofocus>
	      	<input type="password" name="password" class="form-control mb-2" placeholder="Password" required>
	
	      <button class="btn btn-lg btn-primary btn-block mb-2" type="submit" value="login">Sign in</button>
	      <a href="register.jsp" class="text-center"><p>Register</p></a>
	      <p class="mt-2 mb-2 text-muted text-center">${message}</p>
	    </form>
	        </div>
	    </div>
    </div>
    
</main>
<!-- End Main content -->

<%@ include file="footer.jsp" %>