package gr.aueb.cf.finalproject.repository;

import gr.aueb.cf.finalproject.model.DrinkCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkCategoryRepository extends JpaRepository<DrinkCategory, Long> {

    /**
     * Finds a drink category by its name.
     *
     * @param name The name of the drink category.
     * @return The found drink category or null if not found.
     */
    DrinkCategory findByName(String name);

    /**
     * Deletes a drink category by its name.
     *
     * @param name The name of the drink category to delete.
     */
    void deleteByName(String name);
}