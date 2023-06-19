package gr.aueb.cf.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.aueb.cf.finalproject.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds a user by their email.
	 *
	 * @param email The email of the user.
	 * @return The found user or null if not found.
	 */
	User findByEmail(String email);

	/**
	 * Deletes a user by their email.
	 *
	 * @param email The email of the user to delete.
	 */
	void deleteByEmail(String email);
}


