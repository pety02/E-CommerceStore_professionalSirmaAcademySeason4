package model;

import interfaces.Perishable;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Grocery item class
 */
public class GroceryItem extends InventoryItem implements Perishable, Serializable {
    private LocalDateTime expireTill;

    /**
     * Constructs grocery item by default.
     */
    public GroceryItem() {
        super();
        this.setCategory(Category.GROCERY);
        this.setExpireTill(LocalDateTime.now());
    }

    /**
     * Constructs grocery item by given quantity and price.
     * @param quantity the given quantity
     * @param price the given price
     */
    public GroceryItem(int quantity, double price) {
        super(quantity, price);
        this.setCategory(Category.GROCERY);
        this.setExpireTill(LocalDateTime.now());
    }

    /**
     * Constructs grocery item by given quantity, price and expiration date.
     * @param quantity the given quantity
     * @param price the given price
     * @param expireTill the given expiration date
     */
    public GroceryItem(int quantity, double price, LocalDateTime expireTill) {
        super(quantity, price);
        this.setCategory(Category.GROCERY);
        try {
            this.setExpireTill(expireTill);
        } catch (IllegalArgumentException ex) {
            ex.fillInStackTrace();
            this.setExpireTill(LocalDateTime.now());
        }
    }

    /**
     * Checks if the grocery item's expiration date is passed.
     * @return true if the item's expiration date is passed and false in all other cases.
     */
    @Override
    public boolean isPerishable() {
        return expireTill.isBefore(LocalDateTime.now());
    }

    /**
     * Checks if the grocery item is perishable and if it so, sets the quantity of it to zero.
     */
    @Override
    public void handleExpiration() {
        if(this.isPerishable()) {
            this.setQuantity(0);
        }
    }

    /**
     * Expiration Date Getter
     * @return the expiration date
     */
    public LocalDateTime getExpireTill() {
        return expireTill;
    }

    /**
     * Validates and sets the given expiration date to current item's expiration date.
     * @param expireTill the given expiration date
     * @throws IllegalArgumentException if the given expiration date is invalid.
     */
    public void setExpireTill(LocalDateTime expireTill) throws IllegalArgumentException {
        if(LocalDateTime.now().isBefore(expireTill) || expireTill.equals(LocalDateTime.now())) {
            this.expireTill = expireTill;
        } else {
            throw new IllegalArgumentException("Expiration date should be after or equal to current date.");
        }
    }

    /**
     * Predefined toString method
     * @return representation of the grocery item as a string.
     */
    @Override
    public String toString() {
        return "GroceryItem { " +
                "expireTill: " + expireTill +
                " }";
    }
}