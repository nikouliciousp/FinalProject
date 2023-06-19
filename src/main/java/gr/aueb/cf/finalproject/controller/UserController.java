package gr.aueb.cf.finalproject.controller;

import gr.aueb.cf.finalproject.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import gr.aueb.cf.finalproject.service.UserService;
import gr.aueb.cf.finalproject.dto.UserDto;

import javax.validation.Valid;

/**
 * The UserController class handles user-related requests and operations for regular users.
 */
@Component
@Controller
@RequestMapping("/index_user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	/**
	 * Initializes the user registration DTO as a model attribute.
	 *
	 * @return The user registration DTO.
	 */
	@ModelAttribute("user")
	public UserDto userRegistrationDto() {
		return new UserDto();
	}

	/**
	 * Handles the GET request for displaying the user profile page.
	 * Retrieves the authenticated user from the authentication object and passes it to the view.
	 *
	 * @param model          The model object to be populated with data for the view.
	 * @param authentication The authentication object representing the authenticated user.
	 * @return The user profile page view.
	 */
	@GetMapping
	public String showUserProfile(Model model, Authentication authentication) {
		User user = userService.getUserByUsername(authentication.getName());
		model.addAttribute("user", user);
		return "index_user";
	}

	/**
	 * Handles the GET request for displaying the edit user form.
	 * Checks if the authenticated user is the same as the user being edited.
	 * If they match, retrieves the user by ID and passes it to the view for editing.
	 *
	 * @param id              The ID of the user to be edited.
	 * @param model           The model object to be populated with data for the view.
	 * @param authentication  The authentication object representing the authenticated user.
	 * @return The edit user form view if the user matches, otherwise redirects to the user profile page.
	 */
	@GetMapping("/edit_user/{id}")
	public String showEditUserForm(@PathVariable("id") Long id, Model model, Authentication authentication) {
		String loggedInUserId = authentication.getName();

		User user = userService.getUserById(id);
		if (user != null && user.getEmail().equals(loggedInUserId)) {
			model.addAttribute("user", user);
			return "edit_user";
		} else {
			return "redirect:/index_user";
		}
	}

	/**
	 * Handles the POST request for updating a user's information.
	 * Checks if the user with the specified ID exists and if the authenticated user is the same as the user being updated.
	 * If they match, updates the user's information and redirects to the edit user form with a success message.
	 * If the user does not exist or the authenticated user is different, redirects to the edit user form with an error message.
	 *
	 * @param id              The ID of the user to be updated.
	 * @param registrationDto The DTO object containing the updated user information.
	 * @param result          The binding result object for validation errors.
	 * @param model           The model object to be populated with data for the view.
	 * @param authentication  The authentication object representing the authenticated user.
	 * @return The redirection URL based on the success or failure of the user update.
	 */
	@PostMapping("/edit_user/{id}")
	public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid UserDto registrationDto,
							 BindingResult result, Model model, Authentication authentication) {

		User existingUser = userService.getUserById(id);
		if (existingUser != null && !existingUser.getId().equals(id)) {
			model.addAttribute("userExists", true);
			return "redirect:/index_user/edit_user/" + id + "?userExists";
		}

		userService.updateUser(registrationDto);
		model.addAttribute("success", true);
		return "redirect:/index_user/edit_user/" + id + "?success";
	}

	/**
	 * Handles the GET request for deleting a user.
	 * Deletes the user with the specified email and redirects to the home page with a success message if deletion is successful.
	 * Otherwise, redirects to the home page with an error message.
	 *
	 * @param email The email of the user to be deleted.
	 * @param model The model object to be populated with data for the view.
	 * @return The redirection URL based on the success or failure of the user deletion.
	 */
	@GetMapping("/delete_user/{email}")
	public String deleteUser(@PathVariable("email") String email, Model model) {
		boolean deleteSuccess = userService.deleteUserByEmail(email);
		if (deleteSuccess) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(null);
			}
			return "redirect:/?deleteSuccess";
		} else {
			return "redirect:/?deleteError";
		}
	}
}
