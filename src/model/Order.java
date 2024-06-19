package model;

import java.util.LinkedList;

/**
 *
 */
public class Order {
    private static int orderNo = 0;
    private int ID;
    private LinkedList<InventoryItem> bucket;
    private double totalPrice;

    /**
     *
     * @param bucket
     */
    public Order(LinkedList<InventoryItem> bucket) {
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
    public LinkedList<InventoryItem> getBucket() {
        return bucket;
    }

    /**
     *
     * @param bucket
     */
    public void setBucket(LinkedList<InventoryItem> bucket) {
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
        for(InventoryItem currentItem : this.bucket) {
            this.totalPrice += currentItem.getPrice();
        }
    }
}