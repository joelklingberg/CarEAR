package assemblage;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (name="CarServlet", urlPatterns = {"/cars"})
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @EJB
    FacadeLocal facade;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = "";
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		
		System.out.println("Car Servlet Action = " + action);
		
		if (action.equals("showCars")) {
			showCars(request, response);
		}
		else if (action.equals("showEditCar")) {
			showEditCar(request, response);
		}
		else if (action.equals("registerCar")) {
			registerCar(request, response);
		}
		else if (action.equals("buyCar")) {
			buyCar(request, response);
		}
		else if (action.equals("sellCar")) {
			sellCar(request, response);
		}
		else if (action.equals("stopSelling")) {
			stopSelling(request, response);
		}
		else if (action.equals("editCar")) {
			editCar(request, response);
		}
		else if (action.equals("deleteCar")) {
			deleteCar(request, response);
		}
		else {
			System.out.println("No action found for action: " + action);
			showCars(request, response);
		}    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/* Servlet actions */
	
	private void showCars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CarBean> cars = facade.showAllCars();
		//ArrayList<CarBean> cars = facade.
		
		HttpSession session = request.getSession(true);
		session.setAttribute("cars", cars);
	    request.getRequestDispatcher("/showCars.jsp").forward(request, response);
	}
	
	private void showEditCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("carId"));
		CarBean editingCar = facade.findById(id);
		HttpSession session = request.getSession(true);
		session.setAttribute("editingCar", editingCar);
	    request.getRequestDispatcher("/editCar.jsp").forward(request, response);
	}
	
	private void registerCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		try {
			UserBean owner = (UserBean) request.getSession().getAttribute("currentSessionUser");
			CarBean car = new CarBean();
			String brand = request.getParameter("brand");
			int price = Integer.parseInt(request.getParameter("price"));
			boolean forSale = false;
			String year = request.getParameter("year");
			String description = request.getParameter("description");
			
			// Check so the user is logged in before proceeding:
			if(owner != null){
				// Logged in.
				car.setOwner(owner.getUsername());
				car.setBrand(brand);
				car.setPrice(price);
				car.setForSale(forSale);
				car.setYear(year);
				car.setDescription(description);
				
				facade.createCar(car);
			} else {
				// Not logged in.
				message = "Not logged in.";
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		
		//Response message
		request.setAttribute("message", message);
		request.getRequestDispatcher("/registerCar.jsp").forward(request, response);
	}

	private void buyCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("carId"));
		
		try {
			UserBean newOwner = (UserBean) request.getSession().getAttribute("currentSessionUser");
			
			// Check so the user is logged in before proceeding:
			if(newOwner != null){
				// Logged in.
				//CarDAO.changeOwner(id, newOwner);
				CarBean car = facade.findById(id);
				
				if (car != null) {
					car.setOwner(newOwner.getUsername());
					facade.updateCar(car);
				}
				
				
				String referer = request.getHeader("Referer");
			    response.sendRedirect(referer);
			} else {
				// Not logged in.
				System.out.println("User not logged in, can not buy car.");
				String message = "Please login before buying cars";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		
	}
	
	private void sellCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("carId"));
		
		try {
			UserBean currentUser = (UserBean) request.getSession().getAttribute("currentSessionUser");
			
			// Check so the user is logged in before proceeding:
			if(currentUser != null){
				// Logged in.
				CarBean car = facade.findById(id);
				// Check so the user owns the car:
				if(car != null) {
					if(car.getOwner().equals(currentUser.getUsername())){
						// Sell the car.
						car.setForSale(true);
						facade.updateCar(car);
					}
				}
				

			} else {
				// Not logged in.
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		String referer = request.getHeader("Referer");
	    response.sendRedirect(referer);
	}
	
	private void stopSelling(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("carId"));
		
		try {
			UserBean currentUser = (UserBean) request.getSession().getAttribute("currentSessionUser");
			
			// Check so the user is logged in before proceeding:
			if(currentUser != null){
				// Logged in.
				CarBean car = facade.findById(id);
				// Check so the user owns the car:
				if(car.getOwner().equals(currentUser.getUsername())){
					// Sell the car.
					car.setForSale(false);
					facade.updateCar(car);
				}

			} else {
				// Not logged in.
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		String referer = request.getHeader("Referer");
	    response.sendRedirect(referer);
	}
	
	private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("carId"));
		
		try {
			UserBean currentUser = (UserBean) request.getSession().getAttribute("currentSessionUser");
			
			// Check so the user is logged in before proceeding:
			if(currentUser != null){
				// Logged in.
				
				CarBean car = facade.findById(id);
				
				// Check so the user owns the car:
				if(car.getOwner().equals(currentUser.getUsername())){
					// Current user owns the car, therefor it is deleted.
					facade.deleteCar(id);
				} else {
					// Current user does not own the car.
					System.out.println("Can not delete car not owned by current user.");
				}
			} else {
				// Not logged in.
				System.out.println("Can not delete car without being logged in.");
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		String referer = request.getHeader("Referer");
	    response.sendRedirect(referer);
	}
	
	private void editCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("carId"));
		
		try {
			UserBean currentUser = (UserBean) request.getSession().getAttribute("currentSessionUser");
			
			// Check so the user is logged in before proceeding:
			if(currentUser != null){
				// Logged in.
				
				CarBean car = facade.findById(id);
				
				// Check so the user owns the car:
				if(car.getOwner().equals(currentUser.getUsername())){
					// Current user owns the car, therefor it is edited.
					
					//CarBean newCar = new CarBean();
					String brand = request.getParameter("brand");
					int price = Integer.parseInt(request.getParameter("price"));
					String year = request.getParameter("year");
					String description = request.getParameter("description");
					
					car.setBrand(brand);
					car.setPrice(price);
					car.setYear(year);
					car.setDescription(description);
					
					facade.updateCar(car);
					
					//request.setAttribute("message", message);
					String url = "cars?action=showEditCar&carId=" + id;
					request.getRequestDispatcher(url).forward(request, response);
				} else {
					// Current user does not own the car.
					System.out.println("Can not edit car not owned by current user.");
					String referer = request.getHeader("Referer");
				    response.sendRedirect(referer);
				}
			} else {
				// Not logged in.
				String message = "Please login before editing car.";
				System.out.println("Can not edit car without being logged in.");
				request.setAttribute("message", message);
				String url = "cars?action=showEditCar&carId=" + id;
				request.getRequestDispatcher(url).forward(request, response);
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		
	}
}
