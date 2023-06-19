package gr.aueb.cf.finalproject.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import gr.aueb.cf.finalproject.model.User;
import gr.aueb.cf.finalproject.dto.UserDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

	/**
	 * Saves a new user.
	 *
	 * @param registrationDto The DTO object containing the user details.
	 * @return The saved user.
	 */
	User save(UserDto registrationDto);

	/**
	 * Retrieves a user by their username.
	 *
	 * @param username The username of the user.
	 * @return The found user or throws UsernameNotFoundException if not found.
	 * @throws UsernameNotFoundException If the user is not found.
	 */
	User getUserByUsername(String username) throws UsernameNotFoundException;

	/**
	 * Retrieves all users.
	 *
	 * @return An iterable collection of users.
	 */
	Iterable<User> getAllUsers();

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param id The ID of the user.
	 * @return The found user or null if not found.
	 */
	User getUserById(Long id);

	/**
	 * Updates an existing user.
	 *
	 * @param registrationDto The DTO object containing the updated user details.
	 */
	void updateUser(UserDto registrationDto);

	/**
	 * Checks if a user with the given email already exists.
	 *
	 * @param email The email of the user.
	 * @return True if the user exists, false otherwise.
	 */
	boolean isUserExists(String email);

	/**
	 * Deletes a user by their email.
	 *
	 * @param email The email of the user to delete.
	 * @return True if the user was successfully deleted, false otherwise.
	 */
	boolean deleteUserByEmail(String email);
}

