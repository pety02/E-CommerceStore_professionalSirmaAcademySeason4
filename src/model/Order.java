package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 */
public class Order implements Serializable {
    private static int orderNo = 0;
    private int ID;
    private Map<InventoryItem, Integer> bucket;
    private double totalPrice;

    /**
     *
     * @param bucket
     */
    public Order(Map<InventoryItem, Integer> bucket) {
        this.setID();
        this.setBucket(bucket);
        this.calculateTotalPrice();
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
        this.ID = ++Order.orderNo;
    }

    /**
     *
     * @return
     */
    public Map<InventoryItem, Integer> getBucket() {
        return bucket;
    }

    /**
     *
     * @param bucket
     */
    public void setBucket(Map<InventoryItem, Integer> bucket) {
        this.bucket = bucket;
    }

    /**
     *
     * @return
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     *
     */
    public void calculateTotalPrice() {
        for(Map.Entry<InventoryItem, Integer> currentItem : this.bucket.entrySet()) {
            this.totalPrice += (currentItem.getKey().getPrice() * currentItem.getValue());
        }
    }

    /**
     *
     * @return
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