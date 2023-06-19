package gr.aueb.cf.finalproject.dto;

/**
 * Data Transfer Object (DTO) for Drinks.
 */
public class DrinkDto {

    private Long id;                    // The ID of the drink.
    private String name;                // The name of the drink.
    private double price;               // The price of the drink.
    private Long drinkCategoryId;       // The ID of the drink category.

    public DrinkDto() {
    }

    /**
     * Constructor for DrinkDto object.
     *
     * @param id               The ID of the drink.
     * @param name             The name of the drink.
     * @param price            The price of the drink.
     * @param drinkCategoryId The ID of the drink category.
     */
    public DrinkDto(Long id, String name, double price, Long drinkCategoryId) {
        this.id = id;
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
     * Retrieves the ID of the drink category.
     *
     * @return The ID of the drink category.
     */
    public Long getDrinkCategoryId() {
        return drinkCategoryId;
    }

    /**
     * Sets the ID of the drink category.
     *
     * @param drinkCategoryId The ID of the drink category.
     */
    public void setDrinkCategoryId(Long drinkCategoryId) {
        this.drinkCategoryId = drinkCategoryId;
    }
}
