package assemblage;

import java.util.ArrayList;

import javax.ejb.Local;

@Local
public interface CarEAOLocal {
	public CarBean findById(int id);
	public String findOwner(int id);
	public CarBean createCar(CarBean car);
	public CarBean updateCar(CarBean car);
	public void deleteCar(int id);
	public ArrayList<CarBean> showAllCars();
}
