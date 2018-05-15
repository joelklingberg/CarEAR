package assemblage;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface FacadeRemote {
	public CarBean findById(int id);
	public String findOwner(int id);
	public CarBean createCar(CarBean car);
	public CarBean updateCar(CarBean car);
	public void deleteCar(int id);
	public UserBean findByUsername(String username);
	public ArrayList<CarBean> findAllOwnedCars(String username);
	public UserBean createUser(UserBean user);
	public UserBean updateUser(UserBean user);
	public void deleteUser(String username);
	public UserBean loginUser(UserBean user);
	public ArrayList<CarBean> showAllCars();
}
