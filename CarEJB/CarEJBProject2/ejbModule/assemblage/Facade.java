package assemblage;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Facade
 */
@Stateless
public class Facade implements FacadeRemote, FacadeLocal {

	@EJB
	private CarEAOLocal CarEAO;
	@EJB
	private UserEAOLocal UserEAO;
	
    /**
     * Default constructor. 
     */
    public Facade() {
        // TODO Auto-generated constructor stub
    }
    
    public CarBean findById(int id){
		return CarEAO.findById(id);
	}

	public String findOwner(int id){
		return CarEAO.findOwner(id);
	} 

	public CarBean createCar(CarBean car){
		return CarEAO.createCar(car);
	}
	
	public CarBean updateCar(CarBean car){
		return CarEAO.updateCar(car);
	}

	public void deleteCar(int id){
		CarEAO.deleteCar(id);
	}

	public UserBean findByUsername(String username) {
		return UserEAO.findByUsername(username);
	}

	public ArrayList<CarBean> findAllOwnedCars(String username){
		return UserEAO.findAllOwnedCars(username);
	}

	public UserBean createUser(UserBean user){
		return UserEAO.createUser(user);
	}

	public UserBean updateUser(UserBean user) {
		return UserEAO.updateUser(user);
	}

	public void deleteUser(String username) {
		UserEAO.deleteUser(username);
	}
	
	public UserBean loginUser(UserBean user){
		UserBean loginUser = UserEAO.findByUsername(user.getUsername());
		
		if(loginUser.getPassword().equals(user.getPassword())) {
			user.setValid(true);
		}
		
		return user;
	}
	
	public ArrayList<CarBean> showAllCars(){
		return CarEAO.showAllCars();
	}
	
}
