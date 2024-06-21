package model;

import java.io.Serializable;

/**
 * Electronics item class
 */
public class ElectronicsItem extends FragileItem implements Serializable {
    private ElectronicsType type;

    /**
     * Constructs the electronics item object by default.
     */
    public ElectronicsItem() {
        super();
        this.setType(ElectronicsType.OTHER);
    }

    /**
     * Constructs the electronics item object by quantity and price.
     * @param quantity the given quantity
     * @param price the given price
     */
    public ElectronicsItem(int quantity, double price) {
        super(quantity, price);
        this.setCategory(Category.ELECTRONICS);
        this.setType(ElectronicsType.OTHER);
    }

    /**
     * Constructs the electronics item object by quantity, price and weight.
     * @param quantity the given quantity
     * @param price the given price
     * @param weight the given weight
     */
    public ElectronicsItem(int quantity, double price, double weight) {
        super(quantity, price, weight);
        this.setCategory(Category.ELECTRONICS);
        this.setType(ElectronicsType.OTHER);
    }

    /**
     * Constructs the electronics item object by quantity, price, weight and type
     * @param quantity the given quantity
     * @param price the given price
     * @param weight the given weight
     * @param type the given type
     */
    public ElectronicsItem(int quantity, double price, double weight, ElectronicsType type) {
        super(quantity, price, weight);
        this.setCategory(Category.ELECTRONICS);
        this.setType(type);
    }

    /**
     * Type Getter
     * @return the type of the electronics item.
     */
    public ElectronicsType getType() {
        return type;
    }

    /**
     * Sets the type of the electronics item by a given type.
     * @param type the given type
     */
    public void setType(ElectronicsType type) {
        this.type = type;
    }

    /**
     * Predefined toString() method
     * @return the representation of the electronics item as a string.
     */
    @Override
    public String toString() {
        return "ElectronicsItem { " +
                "type: " + type +
                " }";
    }
}