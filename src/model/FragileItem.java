package model;

import interfaces.Breakable;

import java.io.Serializable;

/**
 * Fragile item class
 */
public class FragileItem extends InventoryItem implements Breakable, Serializable {
    private double weight;
    private boolean isBroken;

    /**
     * Constructs fragile item object by default.
     */
    public FragileItem() {
        super();
        this.setCategory(Category.FRAGILE);
        this.isBroken = false;
        this.setWeight(0.00);
    }

    /**
     * Constructs fragile item object by given quantity and price.
     * @param quantity the given quantity
     * @param price the given price
     */
    public FragileItem(int quantity, double price) {
        super(quantity, price);
        this.setCategory(Category.FRAGILE);
        this.isBroken = false;
        this.setWeight(0.00);
    }

    /**
     * Constructs fragile item object by given quantity, price and weight.
     * @param quantity the given quantity
     * @param price the given price
     * @param weight the given weight
     */
    public FragileItem(int quantity, double price, double weight) {
        super(quantity, price);
        this.setCategory(Category.FRAGILE);
        this.isBroken = false;

        try {
            this.setWeight(weight);
        } catch (IllegalArgumentException ex) {
            ex.fillInStackTrace();
            this.setWeight(0.00);
        }
    }

    /**
     * Weight Getter
     * @return the fragile item's weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Validates and sets the given weight to the fragile item's weight.
     * @param weight the given weight
     * @throws IllegalArgumentException when the given weight is in invalid.
     */
    public void setWeight(double weight) throws IllegalArgumentException {
        if(0.00 <= weight) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Weight should be positive floating point number.");
        }
    }

    /**
     * Checks if the fragile item can be broken.
     * @return true if the fragile item is not broken yet and false in the opposite case.
     */
    @Override
    public boolean isBreakable() {
        return !this.isBroken;
    }

    /**
     * Checks if the fragile item is still breakable and
     * if it's so sets isBroken field to true.
     */
    @Override
    public void handleBreakage() {
        if(this.isBreakable()) {
            this.isBroken = true;
        }
    }

    /**
     * Predefined toString() method.
     * @return the representation of the fragile item as a string.
     */
    @Override
    public String toString() {
        return "FragileItem { " +
                "weight: " + weight +
                ", isBroken: " + isBroken +
                " }";
    }
}