<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Main content -->
<main>
	
		<div class="container">
	<div class="row align-items-center justify-content-center">
        <div class="col-md-4 col-md-offset-3">
        <form action="users?action=registerUser" method="post" class="form-signin">
      	<h1 class="h3 mb-3 font-weight-normal text-center">Register new user</h1>
      	<input type="text" name="firstname" class="form-control mb-2" placeholder="Firstname" required autofocus>
      	<input type="text" name="lastname" class="form-control mb-2" placeholder="Lastname" required autofocus>
      	<input type="text" name="username" class="form-control mb-2" placeholder="Username" required autofocus>
      	<input type="password" id="password" name="password" class="form-control mb-2" placeholder="Password" required autofocus>
      	<input type="password" id="password_retyped" name="password_retyped" class="form-control mb-2" placeholder="Retype password" required autofocus>

      <button class="btn btn-lg btn-primary btn-block mb-2" id="submit" type="submit" value="register" disabled>Register</button>
      <p class="mt-2 mb-2 text-muted text-center">${message}</p>
    </form>
        </div>
    </div>
	
    </div>
</main>
<!-- End Main content -->

<%@ include file="footer.jsp" %>