package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.dto.DrinkDto;
import gr.aueb.cf.finalproject.model.Drink;

import java.util.List;

public interface DrinkService {

    /**
     * Saves a new drink.
     *
     * @param drinkDto The drink to be saved.
     * @return The saved drink.
     */
    Drink saveDrink(DrinkDto drinkDto);

    /**
     * Retrieves all drinks.
     *
     * @return A list of all drinks.
     */
    List<Drink> getAllDrinks();

    /**
     * Searches and retrieves a drink by its ID.
     *
     * @param id The ID of the drink to search for.
     * @return The found drink, or null if no drink with the specified ID was found.
     */
    Drink getDrinkById(Long id);

    /**
     * Updates the details of a drink.
     *
     * @param drink The drink to be updated.
     * @return The updated drink.
     */
    Drink updateDrink(DrinkDto drinkDto);

    /**
     * Deletes a drink.
     *
     * @param id The ID of the drink to be deleted.
     */
    boolean deleteDrink(Long id);

    /**
     * Retrieves all drinks belonging to a specific category ID.
     *
     * @param categoryId The category ID.
     * @return A list of drinks belonging to the specified category ID.
     */
    List<Drink> getAllDrinksByCategoryId(Long categoryId);

    /**
     * Checks if a drink with the given name exists.
     *
     * @param name The name of the drink.
     * @return True if a drink with the specified name exists, False otherwise.
     */
    boolean isDrinkExists(String name);

    /**
     * Retrieves a drink by its name.
     *
     * @param name The name of the drink.
     * @return The drink with the specified name, or {@code null} if no such drink exists.
     */
    public Drink getDrinkByName(String name);
}
