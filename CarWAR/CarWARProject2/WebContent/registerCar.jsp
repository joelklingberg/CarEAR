<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Main content -->
<main>
	<div class="container">
	<div class="row align-items-center justify-content-center">
        <div class="col-md-4 col-md-offset-3">
        <form action="cars?action=registerCar" method="post" class="form-signin">
      	<h1 class="h3 mb-3 font-weight-normal text-center">Register new car</h1>
      	<input type="text" name="brand" class="form-control mb-2" placeholder="Brand" required autofocus>
      	<input type="text" name="year" class="form-control mb-2" placeholder="Year" required autofocus>
      	<input type="text" name="price" class="form-control mb-2" placeholder="Price" required autofocus>
      	<textarea name="description" class="form-control mb-2" rows="3" placeholder="Car description" required autofocus></textarea>

      <button class="btn btn-lg btn-primary btn-block mb-2" type="submit" value="register">Register car</button>
      <p class="mt-2 mb-2 text-muted text-center">${message}</p>
    </form>
        </div>
    </div>
	
    </div>
</main>
<!-- End Main content -->

<%@ include file="footer.jsp" %>