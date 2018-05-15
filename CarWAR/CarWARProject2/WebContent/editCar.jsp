<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="assemblage.UserBean, assemblage.CarBean" %>

<!-- Main content -->
<main>
<% CarBean editingCar = (CarBean) session.getAttribute("editingCar"); %>
	<div class="container">
	<div class="row align-items-center justify-content-center">
        <div class="col-md-4 col-md-offset-3">
        <form action="cars?action=editCar&carId=<%= editingCar.getId() %>" method="post" class="form-signin">
      	<h1 class="h3 mb-3 font-weight-normal text-center">Edit car</h1>
      	<input type="text" name="brand" class="form-control mb-2" placeholder="Brand" value="<%= editingCar.getBrand() %>" required autofocus>
      	<input type="text" name="year" class="form-control mb-2" placeholder="Year" value="<%= editingCar.getYear() %>" required autofocus>
      	<input type="text" name="price" class="form-control mb-2" placeholder="Price" value="<%= editingCar.getPrice() %>" required autofocus>
      	<textarea name="description" class="form-control mb-2" rows="3" placeholder="Car description" required autofocus><%= editingCar.getDescription() %></textarea>

      <button class="btn btn-lg btn-primary btn-block mb-2" type="submit" value="register">Update details</button>
      <p class="mt-2 mb-2 text-muted text-center">${message}</p>
    </form>
        </div>
    </div>
	
    </div>
</main>
<!-- End Main content -->

<%@ include file="footer.jsp" %>