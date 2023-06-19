package gr.aueb.cf.finalproject.model;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.Pattern;

/**
 * The User class represents a user in the system.
 */
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;                    // The ID of the user.

	@Column(name = "first_name")
	private String firstName;           // The first name of the user.

	@Column(name = "last_name")
	private String lastName;            // The last name of the user.

	//@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
	private String email;               // The email of the user.

	//@Pattern(regexp="[A-Za-z0-9]+")
	private String password;            // The password of the user.

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;     // The roles associated with the user.

	public User() {
	}

	/**
	 * Constructs a User object with the specified details.
	 *
	 * @param firstName The first name of the user.
	 * @param lastName  The last name of the user.
	 * @param email     The email of the user.
	 * @param password  The password of the user.
	 * @param roles     The roles associated with the user.
	 */
	public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	/**
	 * Retrieves the ID of the user.
	 *
	 * @return The ID of the user.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the ID of the user.
	 *
	 * @param id The ID of the user.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the first name of the user.
	 *
	 * @return The first name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the user.
	 *
	 * @param firstName The first name of the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Retrieves the last name of the user.
	 *
	 * @return The last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the user.
	 *
	 * @param lastName The last name of the user.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Retrieves the email of the user.
	 *
	 * @return The email of the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 *
	 * @param email The email of the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the password of the user.
	 *
	 * @return The password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password The password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Retrieves the roles associated with the user.
	 *
	 * @return The roles associated with the user.
	 */
	public Collection<Role> getRoles() {
		return roles;
	}

	/**
	 * Sets the roles associated with the user.
	 *
	 * @param roles The roles associated with the user.
	 */
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName)
				&& Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email)
				&& Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, email, password, roles);
	}
}