package gr.aueb.cf.finalproject.repository;

import gr.aueb.cf.finalproject.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    /**
     * Finds drinks by their category ID.
     *
     * @param categoryId The ID of the category.
     * @return A list of drinks belonging to the specified category ID.
     */
    List<Drink> findByDrinkCategoryId(Long categoryId);

    /**
     * Finds a drink by its name.
     *
     * @param name The name of the drink.
     * @return The found drink or null if not found.
     */
    Drink findByName(String name);
}