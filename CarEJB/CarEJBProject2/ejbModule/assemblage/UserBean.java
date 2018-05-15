package assemblage;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="users")
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private boolean valid;
	private Set<CarBean> ownedCars;			//EN LISTA MED ALLA BILAR DENNA USER ÄGER

	@Id				//PK FÖR CAR BLIR username I DETTA FALL.
	@Column(name="username")
	public String getUsername(){
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="password")
	public String getPassword(){
		return password;
	}
	public void setPassword (String password) {
		this.password = password;
	}

	@Column(name="firstName")
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="lastName")
	public String getlastName(){
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
	public Set<CarBean> getOwnedCars() {
		return ownedCars;
	}
	public void setOwnedCars(Set<CarBean> ownedCars) {
		this.ownedCars = ownedCars;
	}

	@Transient
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}