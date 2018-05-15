package assemblage;

import java.util.ArrayList;

import javax.ejb.Local;

@Local
public interface UserEAOLocal {
	public UserBean findByUsername(String username);
	public ArrayList<CarBean> findAllOwnedCars(String username);
	public UserBean createUser(UserBean user);
	public UserBean updateUser(UserBean user);
	public void deleteUser(String username);
}
