package gr.aueb.cf.finalproject.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gr.aueb.cf.finalproject.model.Role;
import gr.aueb.cf.finalproject.model.User;
import gr.aueb.cf.finalproject.repository.UserRepository;
import gr.aueb.cf.finalproject.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	@Lazy
	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public User save(UserDto registrationDto) {
		// Check if it is the first user registering
		boolean isFirstUser = userRepository.count() == 0;

		// Create a new User object with the provided registration details
		User user = new User(
				registrationDto.getFirstName(),
				registrationDto.getLastName(),
				registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), // Encode the password using BCryptPasswordEncoder
				Arrays.asList(new Role(isFirstUser ? "ROLE_ADMIN" : "ROLE_USER")) // Assign a default role to the user (ADMIN for the first user, USER for others)
		);

		return userRepository.save(user); // Save the user to the repository and return the saved user
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Retrieve the user from the repository based on the provided username (email)
		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		// Create and return a UserDetails object based on the retrieved user
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), // Username (email)
				user.getPassword(), // Encoded password
				mapRolesToAuthorities(user.getRoles()) // Collection of authorities (roles)
		);
	}

	@Override
	public User getUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Helper method to map a collection of Role objects to a collection of GrantedAuthority objects
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())) // Map each Role to a GrantedAuthority
				.collect(Collectors.toList()); // Collect the GrantedAuthorities into a collection
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public boolean isUserExists(String email) {
		User user = userRepository.findByEmail(email);
		return user != null;
	}

	@Override
	public void updateUser(UserDto registrationDto) {
		User user = userRepository.findById(registrationDto.getId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + registrationDto.getId()));

		user.setFirstName(registrationDto.getFirstName());
		user.setLastName(registrationDto.getLastName());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

		userRepository.save(user);
	}

	@Transactional
	@Override
	public boolean deleteUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			userRepository.deleteByEmail(user.getEmail());
			return true;
		}
		return false;
	}

}