package model;

import interfaces.Perishable;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 */
public class GroceryItem extends InventoryItem implements Perishable, Serializable {
    private LocalDateTime expireTill;

    /**
     *
     */
    public GroceryItem() {
        super();
        this.setCategory(Category.GROCERY);
    }

    /**
     *
     * @param quantity
     * @param price
     */
    public GroceryItem(int quantity, double price) {
        super(quantity, price);
        this.setCategory(Category.GROCERY);
    }

    /**
     *
     * @param quantity
     * @param price
     * @param expireTill
     */
    public GroceryItem(int quantity, double price, LocalDateTime expireTill) {
        super(quantity, price);
        this.setCategory(Category.GROCERY);
        try {
            this.setExpireTill(expireTill);
        } catch (IllegalArgumentException ex) {
            ex.fillInStackTrace();
            this.setExpireTill(null);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isPerishable() {
        return expireTill.isBefore(LocalDateTime.now());
    }

    /**
     *
     */
    @Override
    public void handleExpiration() {
        if(this.isPerishable()) {
            this.setQuantity(0);
        }
    }

    /**
     *
     * @return
     */
    public LocalDateTime getExpireTill() {
        return expireTill;
    }

    /**
     *
     * @param expireTill
     * @throws IllegalArgumentException
     */
    public void setExpireTill(LocalDateTime expireTill) throws IllegalArgumentException {
        if(LocalDateTime.now().isBefore(expireTill) || expireTill == null) {
            this.expireTill = expireTill;
        } else {
            throw new IllegalArgumentException("Expiration date should be after or equal to current date.");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "GroceryItem { " +
                "expireTill: " + expireTill +
                " }";
    }
}