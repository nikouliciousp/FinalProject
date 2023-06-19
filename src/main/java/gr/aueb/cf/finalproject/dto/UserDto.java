package gr.aueb.cf.finalproject.dto;

/**
 * Data Transfer Object (DTO) for Users.
 */
public class UserDto {
	private Long id; 			// The ID of the user.
	private String firstName; 	// The first name of the user.
	private String lastName; 	// The last name of the user.
	private String email; 		// The email of the user.
	private String password; 	// The password of the user.
	private String role;        // The role of the user.

	public UserDto() {
	}

	/**
	 * Constructs a UserDto object with the specified ID, first name, last name, email, and password.
	 *
	 * @param id         The ID of the user.
	 * @param firstName  The first name of the user.
	 * @param lastName   The last name of the user.
	 * @param email      The email of the user.
	 * @param password   The password of the user.
	 */
	public UserDto(Long id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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
}

