package gr.aueb.cf.finalproject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The MainController class handles the main routes and requests for the application.
 */
@Controller
public class MainController {

	/**
	 * Handles the GET request for the login page.
	 * @return The login view.
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * Handles the GET request for the home page.
	 * Determines whether the authenticated user is an admin or a regular user
	 * and redirects them accordingly.
	 * @param model The model object to be populated with data for the view.
	 * @param authentication The authentication object containing the user's authentication information.
	 * @return The redirection URL based on the user's role.
	 */
	@GetMapping("/")
	public String home(Model model, Authentication authentication) {
		boolean isAdmin = authentication.getAuthorities()
				.stream()
				.anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

		if (isAdmin) {
			return "redirect:/index_admin";
		}
		return "redirect:/index_user";
	}
}
