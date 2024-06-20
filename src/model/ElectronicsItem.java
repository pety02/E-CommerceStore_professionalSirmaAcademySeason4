package model;

import java.io.Serializable;

/**
 *
 */
public class ElectronicsItem extends FragileItem implements Serializable {
    private ElectronicsType type;

    /**
     *
     */
    public ElectronicsItem() {
        super();
    }

    /**
     *
     * @param quantity
     * @param price
     */
    public ElectronicsItem(int quantity, double price) {
        super(quantity, price);
        this.setCategory(Category.ELECTRONICS);
    }

    /**
     *
     * @param quantity
     * @param price
     * @param weight
     */
    public ElectronicsItem(int quantity, double price, double weight) {
        super(quantity, price, weight);
        this.setCategory(Category.ELECTRONICS);
    }

    /**
     *
     * @param quantity
     * @param price
     * @param weight
     * @param type
     */
    public ElectronicsItem(int quantity, double price, double weight, ElectronicsType type) {
        super(quantity, price, weight);
        this.setCategory(Category.ELECTRONICS);
        this.setType(type);
    }

    /**
     *
     * @return
     */
    public ElectronicsType getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(ElectronicsType type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ElectronicsItem { " +
                "type: " + type +
                " }";
    }
}