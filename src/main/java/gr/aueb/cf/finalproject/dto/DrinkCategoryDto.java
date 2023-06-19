package gr.aueb.cf.finalproject.dto;

/**
 * Data Transfer Object (DTO) for Drink Categories.
 */
public class DrinkCategoryDto {

    private Long id; // The ID of the drink category.
    private String name; // The name of the drink category.

    public DrinkCategoryDto() {
    }

    /**
     * Constructor for DrinkCategoryDto object.
     *
     * @param id   The ID of the drink category.
     * @param name The name of the drink category.
     */
    public DrinkCategoryDto(Long id, String name) {
        this.id = id;
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
}