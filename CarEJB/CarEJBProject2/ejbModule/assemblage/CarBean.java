package assemblage;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries ({
	@NamedQuery (
		name = "CarBean.showAllCars",
		query = "SELECT c FROM CarBean c"
	)
})
@Table(name="cars")
public class CarBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String owner;
	private String brand;
	private String year;
	private int price;
	private boolean forSale;
	private int id;
	private String description;

	@Id				//PK FÖR CAR BLIR ID I DETTA FALL.
	@Column(name="id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId(){
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="owner")
	public String getOwner(){
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name="brand")
	public String getBrand(){
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name="year")
	public String getYear(){
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	@Column(name="price")
	public int getPrice(){
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@Column(name="forsale")
	public boolean getForSale(){
		return forSale;
	}
	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	@Column(name="description")
	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

