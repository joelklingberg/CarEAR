package assemblage;

import java.awt.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class CarEAOImpl
 */
@Stateless
public class CarEAOImpl implements CarEAOLocal {

    /**
     * Default constructor. 
     */
    public CarEAOImpl() {
        // TODO Auto-generated constructor stub
    }
    
	@PersistenceContext(unitName = "punit")
	private EntityManager em;

	public CarBean findById(int id) {
		return em.find(CarBean.class, id);
	}

	public String findOwner(int id) {
		CarBean c = this.findById(id);

		if (c != null) {
			return c.getOwner();
		} else {
			return null;
		}
	}

	public CarBean createCar(CarBean car) {
		em.persist(car);
		return car;
	}

	public CarBean updateCar(CarBean car) {
		em.merge(car);
		return car;
	}

	public void deleteCar(int id) {
		CarBean c = this.findById(id);
		if (c != null) {
			em.remove(c);
		}
	}
	
	public ArrayList<CarBean> showAllCars(){
		TypedQuery<CarBean> query = em.createNamedQuery("CarBean.showAllCars", CarBean.class);
		
		java.util.List<CarBean> list = query.getResultList();
		return (ArrayList<CarBean>) list;
	}

}
