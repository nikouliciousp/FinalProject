package gr.aueb.cf.finalproject.model;

import javax.persistence.*;

/**
 * The Drink class represents a drink in the system.
 */
@Entity
@Table(name = "drink")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "drink_category_id") // Add column for the drink category ID
    private Long drinkCategoryId;

    public Drink() {
    }

    /**
     * Constructs a Drink object with the specified name and drink category ID.
     *
     * @param name              The name of the drink.
     * @param price             The price of the drink.
     * @param drinkCategoryId   The ID of the drink category to which the drink belongs.
     */
    public Drink(String name, Double price, Long drinkCategoryId) {
        this.name = name;
        this.price = price;
        this.drinkCategoryId = drinkCategoryId;
    }

    /**
     * Retrieves the ID of the drink.
     *
     * @return The ID of the drink.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the drink.
     *
     * @param id The ID of the drink.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the drink.
     *
     * @return The name of the drink.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the drink.
     *
     * @param name The name of the drink.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the price of the drink.
     *
     * @return The price of the drink.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the drink.
     *
     * @param price The price of the drink.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the ID of the drink category to which the drink belongs.
     *
     * @return The ID of the drink category.
     */
    public Long getDrinkCategoryId() {
        return drinkCategoryId;
    }

    /**
     * Sets the ID of the drink category to which the drink belongs.
     *
     * @param drinkCategoryId The ID of the drink category.
     */
    public void setDrinkCategoryId(Long drinkCategoryId) {
        this.drinkCategoryId = drinkCategoryId;
    }
}