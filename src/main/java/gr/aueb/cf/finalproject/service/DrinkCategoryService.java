package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.dto.DrinkCategoryDto;
import gr.aueb.cf.finalproject.model.DrinkCategory;

public interface DrinkCategoryService {

    /**
     * Saves a new drink category.
     *
     * @param drinkCategoryDto The DTO object containing the drink category details.
     * @return The saved drink category.
     */
    DrinkCategory save(DrinkCategoryDto drinkCategoryDto);

    /**
     * Retrieves a drink category by its ID.
     *
     * @param id The ID of the drink category.
     * @return The found drink category or null if not found.
     */
    DrinkCategory getDrinkCategoryById(Long id);

    /**
     * Retrieves all drink categories.
     *
     * @return An iterable collection of drink categories.
     */
    Iterable<DrinkCategory> getAllDrinkCategories();

    /**
     * Updates an existing drink category.
     *
     * @param drinkCategoryDto The DTO object containing the updated drink category details.
     */
    void updateDrinkCategory(DrinkCategoryDto drinkCategoryDto);

    /**
     * Deletes a drink category by its ID.
     *
     * @param id The ID of the drink category to delete.
     * @return True if the drink category was successfully deleted, false otherwise.
     */
    boolean deleteDrinkCategoryById(Long id);

    /**
     * Checks if a drink category with the given name already exists.
     *
     * @param name The name of the drink category.
     * @return True if the drink category exists, false otherwise.
     */
    boolean isDrinkCategoryExists(String name);

    /**
     * Retrieves a drink category by its name.
     *
     * @param name The name of the drink category.
     * @return The found drink category or null if not found.
     */
    DrinkCategory getDrinkCategoryByName(String name);
}

