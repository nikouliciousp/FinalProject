package gr.aueb.cf.finalproject.controller;

import gr.aueb.cf.finalproject.dto.UserDto;
import gr.aueb.cf.finalproject.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The RegistrationController class handles user registration requests and operations.
 */
@Component
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
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
     * Handles the GET request for displaying the registration form.
     * Adds an error attribute to the model if there is an error parameter in the request.
     *
     * @param error The error parameter in the request (optional).
     * @param model The model object to be populated with data for the view.
     * @return The registration form view.
     */
    @GetMapping
    public String showRegistrationForm(@RequestParam(name = "error", required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "registration";
    }

    /**
     * Handles the POST request for registering a new user.
     * Checks if the user already exists, saves the new user, and redirects to the registration form with a success message.
     * If the user already exists, redirects to the registration form with an error message.
     *
     * @param registrationDto      The DTO object containing the user registration data.
     * @param redirectAttributes   The redirect attributes object to add error or success messages.
     * @return The redirection URL based on the success or failure of the user registration.
     */
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDto registrationDto, RedirectAttributes redirectAttributes) {
        if (userService.isUserExists(registrationDto.getEmail())) {
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/registration";
        }

        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
