<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="assemblage.UserBean, assemblage.CarBean, java.util.ArrayList" %>

<!-- Main content -->
<main>

	<div class="container">
		<h1 class="jumbotron-heading">All cars</h1>
	</div>

	<% ArrayList<CarBean> cars = (ArrayList<CarBean>) session.getAttribute("cars"); %>
	
	<!-- Car album -->
	<div class="album py-5 bg-light">
		<div class="container">
			<div class="row">
	<% for(CarBean car : cars){ %>
		<% boolean yourCar = false; %>
		<% if(currentUser != null) { %>
			<% if (car.getOwner().equals(currentUser.getUsername())) { %>
				<% yourCar = true; %>
			<% } %>
		<% } %>
				<!-- Car card -->
				<div class="col-md-4">
					<div class="card mb-4 box-shadow">
					<!-- <img class="card-img-top" data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail" alt="Card image"> -->
                		<div class="card-body">
                		<% if (yourCar) { %>
	                		<div class="dropdown">
		                      <button class="btn btn-sm dropdown-toggle float-right" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                      </button>
		                      <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		                        <a class="dropdown-item" href="cars?action=showEditCar&carId=<%= car.getId() %>">Edit car</a>
		                        <div class="dropdown-divider"></div>
		                        <a class="dropdown-item" href="" data-href="cars?action=deleteCar&carId=<%= car.getId() %>" data-toggle="modal" data-target="#confirm-delete">Delete car</a>
		                      </div>
		                    </div>
		                <% } %>
							<h5 class="card-title"><%= car.getBrand() %></h5>
                    		<h6 class="card-subtitle mb-2 text-muted"><%= car.getYear() %></h6>
                    		<h6 class="card-subtitle mb-2 text-muted"><%= car.getPrice() %> SEK</h6>
                  			<p class="card-text"><%= car.getDescription() %></p>
                    
                  			<div class="d-flex justify-content-between align-items-center">
                    			 <% if (car.getForSale() && yourCar) { %>
                    				<a href="cars?action=stopSelling&carId=<%= car.getId() %>" class="btn btn-warning my-2">Stop selling</a>
                    			<% } %>
                    			<% if (!car.getForSale() && yourCar) { %>
                    				<a href="cars?action=sellCar&carId=<%= car.getId() %>" class="btn btn-success my-2">Sell</a>
                    			<% } %>
                    			<% if (!car.getForSale() && !yourCar) { %>
                    				<h6 class="card-subtitle mb-2 text-muted">Not for sale</h6>
                    			<% } %>
                  				<% if (car.getForSale() && !yourCar) { %>
                    				<a href="cars?action=buyCar&carId=<%= car.getId() %>" class="btn btn-primary my-2">Buy</a>
                    			<% } %>
                      			<a href="users?action=showProfile&username=<%= car.getOwner() %>" class="card-link text-muted"><%= car.getOwner() %></a>
                  			</div>
                		</div>
              		</div>
            	</div>
    			<!-- End Car card -->
	<% } %>
			</div>
        </div>
    </div>
	<!-- End Car album -->
	
	<!-- Confirm deletion modal -->
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header text-left">
                    <h4 class="modal-title text-center" id="myModalLabel">Confirm Delete</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
            
                <div class="modal-body">
                    <p>You are about to delete this car, this procedure is irreversible.</p>
                    <p>Do you wish to proceed?</p>
                    <p class="debug-url"></p>
                </div>
                
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-danger btn-ok">Delete</a>
                </div>
            </div>
        </div>
    </div>
    <!-- End Confirm deletion modal -->
	
</main>
<!-- End Main content -->

<%@ include file="footer.jsp" %>