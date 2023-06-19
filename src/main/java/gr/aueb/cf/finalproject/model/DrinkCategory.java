package gr.aueb.cf.finalproject.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * The DrinkCategory class represents a category of drinks in the system.
 */
@Entity
@Table(name = "drink_category", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class DrinkCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "drinkCategoryId")
    private Collection<Drink> drinks;

    public DrinkCategory() {
    }

    /**
     * Constructs a DrinkCategory object with the specified name.
     *
     * @param name The name of the drink category.
     */
    public DrinkCategory(String name) {
        this.name = name;
    }

    /**
     * Retrieves the ID of the drink category.
     *
     * @return The ID of the drink category.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the drink category.
     *
     * @param id The ID of the drink category.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the drink category.
     *
     * @return The name of the drink category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the drink category.
     *
     * @param name The name of the drink category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the drinks in the drink category.
     *
     * @return The drinks in the drink category.
     */
    public Collection<Drink> getDrinks() {
        return drinks;
    }

    /**
     * Sets the drinks in the drink category.
     *
     * @param drinks The drinks in the drink category.
     */
    public void setDrinks(Collection<Drink> drinks) {
        this.drinks = drinks;
    }
}