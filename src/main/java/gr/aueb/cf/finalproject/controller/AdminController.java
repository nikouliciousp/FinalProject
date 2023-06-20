package gr.aueb.cf.finalproject.controller;

import gr.aueb.cf.finalproject.dto.DrinkCategoryDto;
import gr.aueb.cf.finalproject.dto.DrinkDto;
import gr.aueb.cf.finalproject.model.Drink;
import gr.aueb.cf.finalproject.model.DrinkCategory;
import gr.aueb.cf.finalproject.model.User;
import gr.aueb.cf.finalproject.service.DrinkCategoryService;
import gr.aueb.cf.finalproject.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import gr.aueb.cf.finalproject.service.UserService;
import gr.aueb.cf.finalproject.dto.UserDto;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * AdminController class handles the requests and business logic related to the admin dashboard.
 */
@Controller
@RequestMapping("/index_admin")
public class AdminController {

    private final UserService userService;
    private final DrinkCategoryService drinkCategoryService;
    private final DrinkService drinkService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AdminController(UserService userService, DrinkCategoryService drinkCategoryService, DrinkService drinkService) {
        this.userService = userService;
        this.drinkCategoryService = drinkCategoryService;
        this.drinkService = drinkService;
    }

    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto();
    }

    @ModelAttribute("drinkCategory")
    public DrinkCategoryDto drinkCategoryDto() {
        return new DrinkCategoryDto();
    }

    @ModelAttribute("drinks")
    public DrinkDto drinkDto() {
        return new DrinkDto();
    }

    /**
     * Handles the GET request for the admin dashboard.
     * Fetches all users and drink categories and sorts them by ID.
     * Adds the sorted lists as model attributes for the view.
     * @param model The model object to be populated with data for the view.
     * @return The admin dashboard view.
     */
    @GetMapping
    public String showRegistrationForm(Model model) {
        Iterable<User> users = userService.getAllUsers();
        List<User> sortedUsers = new ArrayList<>();
        users.forEach(sortedUsers::add);
        Collections.sort(sortedUsers, Comparator.comparing(User::getId));
        model.addAttribute("users", sortedUsers);

        Iterable<DrinkCategory> drinkCategories = drinkCategoryService.getAllDrinkCategories();
        List<DrinkCategory> sortedDrinkCategories = new ArrayList<>();
        drinkCategories.forEach(sortedDrinkCategories::add);
        Collections.sort(sortedDrinkCategories, Comparator.comparing(DrinkCategory::getId));
        model.addAttribute("drinkCategories", sortedDrinkCategories);
        return "index_admin";
    }

    /**
     * Handles the POST request for adding a new user by the admin.
     * Validates the user input, checks if the user already exists, and saves the new user.
     * @param registrationDto The DTO object containing the user's registration data.
     * @param result The binding result object for validation errors.
     * @param model The model object to be populated with data for the view.
     * @return The redirection URL based on the success or failure of the user registration.
     */
    @PostMapping("/add_user_admin")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto registrationDto,
                                      BindingResult result, Model model) {
        if (userService.isUserExists(registrationDto.getEmail())) {
            model.addAttribute("userExists", true);
            return "add_user_admin";
        }

        if (result.hasErrors()) {
            return "index_admin";
        }

        userService.save(registrationDto);
        model.addAttribute("success", true);
        return "redirect:/index_admin/add_user_admin?success";
    }

    /**
     * Handles the GET request for displaying the form to add a new user by the admin.
     * @param model The model object to be populated with data for the view.
     * @return The add user form view.
     */
    @GetMapping("/add_user_admin")
    public String showAddUserForm(Model model) {
        return "add_user_admin";
    }

    /**
     * Handles the GET request for displaying the form to edit a user by the admin.
     * Fetches the user by ID and passes it to the view.
     * @param id The ID of the user to be edited.
     * @param model The model object to be populated with data for the view.
     * @return The edit user form view.
     */
    @GetMapping("/edit_user_admin/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "edit_user_admin";
        } else {
            return "redirect:/index_admin";
        }
    }

    /**
     * Handles the POST request for updating a user by the admin.
     * Validates the user input, checks if another user with the same email already exists,
     * and updates the user.
     * @param id The ID of the user to be updated.
     * @param registrationDto The DTO object containing the updated user data.
     * @param result The binding result object for validation errors.
     * @param model The model object to be populated with data for the view.
     * @return The redirection URL based on the success or failure of the user update.
     */
    @PostMapping("/edit_user_admin/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid UserDto registrationDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/index_admin/edit_user_admin/" + id + "?userExists";
        }

        User existingUser = userService.getUserByUsername(registrationDto.getEmail());
        if (existingUser != null && !existingUser.getId().equals(id)) {
            model.addAttribute("userExists", true);
            return "redirect:/index_admin/edit_user_admin/" + id + "?userExists";
        }

        userService.updateUser(registrationDto);
        model.addAttribute("success", true);
        return "redirect:/index_admin/edit_user_admin/" + id + "?success";
    }

    /**
     * Handles the GET request for deleting a user by the admin.
     * Deletes the user by email.
     * @param email The email of the user to be deleted.
     * @param model The model object to be populated with data for the view.
     * @return The redirection URL based on the success or failure of the user deletion.
     */
    @GetMapping("/delete_user_admin/{email}")
    public String deleteUser(@PathVariable("email") String email, Model model) {
        boolean deleteSuccess = userService.deleteUserByEmail(email);
        if (deleteSuccess) {
            return "redirect:/index_admin"+ "?deleteSuccess";
        } else {
            return "redirect:/index_admin"+ "?deleteError";
        }
    }

    /**
     * Handles the GET request for displaying the form to add a new drink category by the admin.
     * @param model The model object to be populated with data for the view.
     * @return The add drink category form view.
     */
    @GetMapping("/add_drink_category_admin")
    public String showAddDrinkCategoryForm(Model model) {
        return "add_drink_category_admin";
    }

    /**
     * Handles the POST request for adding a new drink category by the admin.
     * Validates the drink category input, checks if the drink category already exists,
     * and saves the new drink category.
     * @param drinkCategoryDto The DTO object containing the drink category data.
     * @param result The binding result object for validation errors.
     * @param model The model object to be populated with data for the view.
     * @return The redirection URL based on the success or failure of the drink category addition.
     */
    @PostMapping("/add_drink_category_admin")
    public String addDrinkCategory(@ModelAttribute("drinkCategory") @Valid DrinkCategoryDto drinkCategoryDto,
                                   BindingResult result, Model model) {
        if (drinkCategoryService.isDrinkCategoryExists(drinkCategoryDto.getName())) {
            model.addAttribute("error", true);
            return "add_drink_category_admin";
        }

        if (result.hasErrors()) {
            return "index_admin";
        }

        drinkCategoryService.save(drinkCategoryDto);
        model.addAttribute("success", true);
        return "redirect:/index_admin/add_drink_category_admin?success";
    }

    /**
     * Handles the GET request for deleting a drink category by the admin.
     * Deletes the drink category by ID.
     * @param id The ID of the drink category to be deleted.
     * @param model The model object to be populated with data for the view.
     * @return The redirection URL based on the success or failure of the drink category deletion.
     */
    @GetMapping("/delete_drink_category_admin/{id}")
    public String deleteDrinkCategory(@PathVariable("id") Long id, Model model) {
        boolean deleteSuccess = drinkCategoryService.deleteDrinkCategoryById(id);
        if (deleteSuccess) {
            return "redirect:/index_admin"+ "?deleteDSuccess";
        } else {
            return "redirect:/index_admin"+ "?deleteDError";
        }
    }

    /**
     * Handles the GET request for displaying the form to edit a drink category by the admin.
     * Fetches the drink category by ID and passes it to the view.
     * @param id The ID of the drink category to be edited.
     * @param model The model object to be populated with data for the view.
     * @return The edit drink category form view.
     */
    @GetMapping("/edit_drink_category_admin/{id}")
    public String showEditDrinkCategoryForm(@PathVariable("id") Long id, Model model) {
        DrinkCategory drinkCategory = drinkCategoryService.getDrinkCategoryById(id);
        if (drinkCategory != null) {
            model.addAttribute("drinkCategory", drinkCategory);
            return "edit_drink_category_admin";
        } else {
            return "redirect:/index_admin";
        }
    }

    /**
     * Handles the POST request for updating a drink category by the admin.
     * Validates the drink category input, checks if another drink category with the same name already exists,
     * and updates the drink category.
     * @param id The ID of the drink category to be updated.
     * @param drinkCategoryDto The DTO object containing the updated drink category data.
     * @param result The binding result object for validation errors.
     * @param model The model object to be populated with data for the view.
     * @return The redirection URL based on the success or failure of the drink category update.
     */
    @PostMapping("/edit_drink_category_admin/{id}")
    public String updateDrinkCategory(@PathVariable("id") Long id, @ModelAttribute("drinkCategory") @Valid DrinkCategoryDto drinkCategoryDto,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/index_admin/edit_drink_category_admin/" + id + "?categoryExists";
        }

        DrinkCategory existingDrinkCategory = drinkCategoryService.getDrinkCategoryByName(drinkCategoryDto.getName());
        if (existingDrinkCategory != null && !existingDrinkCategory.getId().equals(id)) {
            model.addAttribute("categoryExists", true);
            return "redirect:/index_admin/edit_drink_category_admin/" + id + "?categoryExists";
        }

        drinkCategoryService.updateDrinkCategory(drinkCategoryDto);
        model.addAttribute("success", true);
        return "redirect:/index_admin/edit_drink_category_admin/" + id + "?success";
    }

    /**
     * Handles the GET request for displaying all drinks for a specific category in the admin view.
     * Retrieves the list of drinks based on the provided category ID and the corresponding drink category.
     * Populates the model with the drinks and drink category data for the view.
     * @param categoryId The ID of the category for which to display the drinks.
     * @param model The model object to be populated with data for the view.
     * @return The view name for displaying all drinks in the admin view.
     */
    @GetMapping("/all_drinks_admin/{id}")
    public String showAllDrinksAdmin(@PathVariable("id") Long categoryId, Model model) {

        List<Drink> drinks = drinkService.getAllDrinksByCategoryId(categoryId);
        DrinkCategory drinkCategory = drinkCategoryService.getDrinkCategoryById(categoryId);

        model.addAttribute("drinks", drinks);
        model.addAttribute("drinkCategory", drinkCategory);

        return "all_drinks_admin";
    }

    @GetMapping("/add_drink_admin/{categoryId}")
    public String showAddDrinkForm(@PathVariable("categoryId") Long categoryId, Model model) {
        DrinkCategory drinkCategory = drinkCategoryService.getDrinkCategoryById(categoryId);
        model.addAttribute("drinkCategory", drinkCategory);
        model.addAttribute("drink", new DrinkDto());
        return "add_drink_admin";
    }

    /**
     * Handles the POST request for adding a new drink by the admin.
     * Validates the drink input and saves the new drink.
     * @param drinkDto The DTO object containing the drink data.
     * @param result The binding result object for validation errors.
     * @param model The model object to be populated with data for the view.
     * @param request The HttpServletRequest object to retrieve additional parameters.
     * @param redirectAttributes The RedirectAttributes object for flash attributes.
     * @return The redirection URL based on the success or failure of the drink addition.
     */
    @PostMapping("/add_drink_admin")
    public String addDrink(@ModelAttribute("drink") @Valid DrinkDto drinkDto,
                           BindingResult result, Model model, HttpServletRequest request,
                           RedirectAttributes redirectAttributes) {

        // Retrieve the selected drink category name from the request parameter
        String categoryName = request.getParameter("drinkCategory.name");

        // Get the DrinkCategory object based on the category name
        DrinkCategory drinkCategory = drinkCategoryService.getDrinkCategoryByName(categoryName);

        // Set the drink category ID in the DrinkDto object
        drinkDto.setDrinkCategoryId(drinkCategory.getId());

        // Check if the drink already exists
        if (drinkService.isDrinkExists(drinkDto.getName())) {
            model.addAttribute("drinkExists", true);
            return "redirect:/index_admin/add_drink_admin/" + drinkDto.getDrinkCategoryId() + "?drinkExists";
        }

        // Check for validation errors
        if (result.hasErrors()) {
            return "/add_drink_admin";
        }

        // Save the new drink
        drinkService.saveDrink(drinkDto);

        // Add a flash attribute for success message
        redirectAttributes.addFlashAttribute("success", true);

        // Redirect to the appropriate URL based on the success or failure of the drink addition
        return "redirect:/index_admin/add_drink_admin/" + drinkDto.getDrinkCategoryId() + "?success";
    }

    /**
     * Handles the GET request for deleting a drink by the admin.
     * Deletes the drink with the specified ID and redirects to the appropriate URL.
     * @param id The ID of the drink to be deleted.
     * @param categoryName The name of the drink category.
     * @param redirectAttributes The RedirectAttributes object for flash attributes.
     * @return The redirection URL based on the success or failure of the drink deletion.
     */
    @GetMapping("/delete_drink_admin/{id}")
    public String deleteDrink(@PathVariable("id") Long id, @RequestParam("categoryName") String categoryName,
                              RedirectAttributes redirectAttributes) {

        // Get the DrinkCategory object based on the category name
        DrinkCategory drinkCategory = drinkCategoryService.getDrinkCategoryByName(categoryName);

        // Delete the drink with the specified ID and check if the deletion was successful
        boolean deleteSuccess = drinkService.deleteDrink(id);

        // Redirect to the appropriate URL based on the success or failure of the drink deletion
        if (deleteSuccess) {
            return "redirect:/index_admin/all_drinks_admin/" + drinkCategory.getId() + "?deleteSuccess";
        } else {
            return "redirect:/index_admin/all_drinks_admin/" + drinkCategory.getId() + "?deleteError";
        }
    }

    /**
     * Handles the GET request for displaying the form to edit a drink by the admin.
     * Fetches the drink by ID and passes it to the view.
     * @param id The ID of the drink to be edited.
     * @param model The model object to be populated with data for the view.
     * @return The edit drink form view.
     */
    @GetMapping("/edit_drink_admin/{id}")
    public String showEditDrinkForm(@PathVariable("id") Long id, Model model) {
        Drink drink = drinkService.getDrinkById(id);
        if (drink != null) {
            model.addAttribute("drink", drink);
            return "edit_drink_admin";
        } else {
            return "redirect:/index_admin";
        }
    }

    /**
     * Handles the POST request for updating a drink by the admin.
     * Validates the drink input, checks if another drink with the same name already exists,
     * and updates the drink.
     * @param id The ID of the drink to be updated.
     * @param drinkDto The DTO object containing the updated drink data.
     * @param result The binding result object for validation errors.
     * @param model The model object to be populated with data for the view.
     * @return The redirection URL based on the success or failure of the drink update.
     */
    @PostMapping("/edit_drink_admin/{id}")
    public String updateDrink(@PathVariable("id") Long id, @ModelAttribute("drink") @Valid DrinkDto drinkDto,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/index_admin/edit_drink_admin/" + id + "?drinkExists";
        }

        Drink existingDrink = drinkService.getDrinkByName(drinkDto.getName());
        if (existingDrink != null && !existingDrink.getId().equals(id)) {
            model.addAttribute("drinkExists", true);
            return "redirect:/index_admin/edit_drink_admin/" + id + "?drinkExists";
        }

        drinkService.updateDrink(drinkDto);
        model.addAttribute("success", true);
        return "redirect:/index_admin/edit_drink_admin/" + id + "?success";
    }
}

