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

@WebServlet (name="UserServlet", urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @EJB
    FacadeLocal facade;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("User Servlet Action = " + action);
		
		if(action.equals("loginUser")) {
			loginUser(request, response);
		} 
		else if (action.equals("registerUser")) {
			registerUser(request, response);
		}
		else if (action.equals("showProfile")) {
			showProfile(request, response);
		}
		else if (action.equals("logoutUser")) {
			logoutUser(request, response);
		} 
		else {
			System.out.println("No action found for action: " + action);
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
	
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		try {
			UserBean user = new UserBean();
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			
			user = facade.loginUser(user);
			
			if (user.isValid()) {
				// Respond with logged in page
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user);
				response.sendRedirect("cars?action=showCars");
			} else {
				// Respond with invalid login page
				//response.sendRedirect("invalidLogin.jsp");
				message = "Incorrect username or password";
			}
		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		
		//Response message
				request.setAttribute("message", message);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		try {
			UserBean user = new UserBean();
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setFirstName(request.getParameter("firstname"));
			user.setLastName(request.getParameter("lastname"));
			
			facade.createUser(user);
			// user response

		} catch (Error e){
			System.out.println(e.getStackTrace());
		}
		
		//Response message
		request.setAttribute("message", message);
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	private void showProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		try {
			UserBean viewingUser = facade.findByUsername(username);
			ArrayList<CarBean> viewingUserCars = facade.findAllOwnedCars(username);
			if (viewingUser != null) {
				// Found user profile.
				HttpSession session = request.getSession(true);
				session.setAttribute("viewingUser", viewingUser);
				session.setAttribute("viewingUserCars", viewingUserCars);
				
				request.getRequestDispatcher("/profile.jsp").forward(request, response);
			} else {
				// Could not find user profile
				System.out.println("Could not find user profile for username: " + username);
				// 404 page.
				response.sendRedirect("404.jsp");
			}
		} catch (Error e){
			System.out.println("Error in UserServlet showProfile action: " + e.getStackTrace());
		}
		
	}
	
	private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.removeAttribute("currentSessionUser");
		
		//String referer = request.getHeader("Referer");
	    //response.sendRedirect(referer);
		response.sendRedirect("login.jsp");
	}
}
