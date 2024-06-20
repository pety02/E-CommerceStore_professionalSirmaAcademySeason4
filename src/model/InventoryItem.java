package model;

import java.io.Serializable;

/**
 *
 */
public class InventoryItem extends AbstractItem implements Serializable {
    private static int ItemNo = 0;
    private int ID;
    private Category category;
    private int quantity;
    private double price;

    /**
     *
     */
    public InventoryItem() {
        this(0, 0.00);
    }

    /**
     *
     * @param quantity
     * @param price
     */
    public InventoryItem(int quantity, double price) {
        this.setID();
        this.setCategory(Category.UNKNOWN);
        try {
            this.setQuantity(quantity);
            this.setPrice(price);
        } catch (IllegalArgumentException ex) {
            ex.fillInStackTrace();
            this.setQuantity(0);
            this.setPrice(0.00);
        }
    }

    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     *
     */
    public void setID() {
        this.ID = ++InventoryItem.ItemNo;
    }

    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     * @throws IllegalArgumentException
     */
    public void setQuantity(int quantity) throws IllegalArgumentException {
        if(0 <= quantity) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity should be positive number.");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Category getCategory() {
        return this.category;
    }

    /**
     *
     * @param category
     */
    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     *
     * @return
     */
    @Override
    public double calculateValue() {
        return this.quantity * this.price;
    }

    /**
     *
     * @return
     */
    @Override
    public String getDetails() {
        return String.format("Inventory interfaces.Item Description:\n{\n\tID: %d,\n\tcategory: \"%s\",\n\tquantity: %d,\n\tprice: %.2f\n}",
                this.ID, this.category.name(), this.quantity, this.price);
    }

    /**
     *
     */
    @Override
    public void displayDescription() {
        System.out.printf("%s%n", this.getDetails());
    }

    /**
     *
     * @return
     */
    @Override
    public double getPrice() {
        return this.price;
    }

    /**
     *
     * @param price
     * @throws IllegalArgumentException
     */
    @Override
    public void setPrice(double price) throws IllegalArgumentException {
        if(0.00 <= price) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price should be positive floating point number.");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "InventoryItem { " +
                "ID: " + ID +
                ", category: " + category +
                ", quantity: " + quantity +
                ", price: " + price +
                " }";
    }
}