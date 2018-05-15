package assemblage;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserEAOImpl
 */
@Stateless
public class UserEAOImpl implements UserEAOLocal {

    /**
     * Default constructor. 
     */
    public UserEAOImpl() {
        // TODO Auto-generated constructor stub
    }
    
	@PersistenceContext(unitName = "punit")
	private EntityManager em;

	public UserBean findByUsername(String username) {
		return em.find(UserBean.class, username);
	}

	public ArrayList<CarBean> findAllOwnedCars(String username) {
		UserBean u = em.find(UserBean.class, username);
		ArrayList<CarBean> list = new ArrayList<CarBean>();

		if (u != null) {
			for (CarBean c : u.getOwnedCars()) {
				list.add(c);
			}
		}
		return list;
	}

	public UserBean createUser(UserBean user) {
		em.persist(user);
		return user;
	}

	public UserBean updateUser(UserBean user) {
		em.merge(user);
		return user;
	}

	public void deleteUser(String username) {
		UserBean u = em.find(UserBean.class, username);
		if (u != null) {
			em.remove(u);
		}
	}

}
