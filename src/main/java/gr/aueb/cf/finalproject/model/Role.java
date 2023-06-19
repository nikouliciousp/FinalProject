package gr.aueb.cf.finalproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Role class represents a user role in the system.
 */
@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;        // The ID of the role.

	private String name;    // The name of the role.

	public Role() {
	}

	/**
	 * Constructs a Role object with the specified name.
	 *
	 * @param name The name of the role.
	 */
	public Role(String name) {
		super();
		this.name = name;
	}

	/**
	 * Retrieves the ID of the role.
	 *
	 * @return The ID of the role.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the ID of the role.
	 *
	 * @param id The ID of the role.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the name of the role.
	 *
	 * @return The name of the role.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the role.
	 *
	 * @param name The name of the role.
	 */
	public void setName(String name) {
		this.name = name;
	}
}