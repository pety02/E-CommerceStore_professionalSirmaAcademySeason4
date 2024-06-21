package model;

import java.io.Serializable;
import java.util.Map;

/**
 * Order class
 */
public class Order implements Serializable {
    private static int orderNo = 0;
    private int ID;
    private Map<InventoryItem, Integer> bucket;
    private double totalPrice;

    /**
     * Constructs an order object by given customer's bucket.
     * @param bucket the given customer's bucket
     */
    public Order(Map<InventoryItem, Integer> bucket) {
        this.setID();
        this.setBucket(bucket);
        this.calculateTotalPrice();
    }

    /**
     * Id Getter
     * @return the id of the current order.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the current order's id as a next orders number.
     */
    public void setID() {
        this.ID = ++Order.orderNo;
    }

    /**
     * Bucket Getter
     * @return the current order's bucket.
     */
    public Map<InventoryItem, Integer> getBucket() {
        return bucket;
    }

    /**
     * Sets a given bucket as a current order's bucket.
     * @param bucket the given bucket
     */
    public void setBucket(Map<InventoryItem, Integer> bucket) {
        this.bucket = bucket;
    }

    /**
     * Total price Getter
     * @return the total price of the current order.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Calculates the total price of the current order.
     */
    public void calculateTotalPrice() {
        for(Map.Entry<InventoryItem, Integer> currentItem : this.bucket.entrySet()) {
            this.totalPrice += (currentItem.getKey().getPrice() * currentItem.getValue());
        }
    }

    /**
     * Predefined toString() method
     * @return the current order object representation as a string.
     */
    @Override
    public String toString() {
        return "Order { " +
                "ID: " + ID +
                ", bucket: " + bucket +
                ", totalPrice: " + totalPrice +
                " }";
    }
}