package model;

import java.io.Serializable;
import java.util.Map;

/**
 *
 */
public class Store implements Serializable {
    private static int storeNo;
    private int ID;
    private String name;
    private Map<InventoryItem, Integer> stock;

    /**
     *
     * @param name
     * @param stock
     */
    public Store(String name, Map<InventoryItem, Integer> stock) {
        this.setID();
        this.setName(name);
        this.setStock(stock);
    }

    /**
     *
     * @param item
     * @param quantity
     */
    public void addInStock(InventoryItem item, int quantity) {
        if(this.stock.containsKey(item)) {
            int currentQuantity = this.stock.get(item) + quantity;
            this.stock.remove(item);
            this.stock.put(item, currentQuantity);
        } else {
            this.stock.put(item, quantity);
        }
    }

    /**
     *
     * @param item
     * @param quantity
     * @throws IllegalArgumentException
     */
    public void removeFromStock(InventoryItem item, int quantity) throws IllegalArgumentException {
        if(this.stock.containsKey(item)) {
            if(this.stock.get(item) < quantity) {
                throw new IllegalArgumentException(
                        String.format("Cannot remove all of these items. There are only %d of this item.",
                                this.stock.get(item)));
            }
            int currentQuantity = this.stock.get(item) - quantity;
            this.stock.remove(item);
            this.stock.put(item, currentQuantity);
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
        this.ID = ++Store.storeNo;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Map<InventoryItem, Integer> getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     */
    public void setStock(Map<InventoryItem, Integer> stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Store { " +
                "ID: " + ID +
                ", name: '" + name + '\'' +
                ", stock: " + stock +
                " }";
    }
}