package model;

import java.io.Serializable;
import java.util.Map;

/**
 * Store class
 */
public class Store implements Serializable {
    private static int storeNo;
    private int ID;
    private String name;
    private Map<InventoryItem, Integer> stock;

    /**
     * Constructs the store by given name and stock.
     * @param name the given name
     * @param stock the given stock
     */
    public Store(String name, Map<InventoryItem, Integer> stock) {
        this.setID();
        this.setName(name);
        this.setStock(stock);
    }

    /**
     * Adds an item in the stock.
     * @param item the given item
     * @param quantity the given stock
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
     * Remove quantity of an item from the store's stock.
     * @param item the given item
     * @param quantity the given quantity
     * @throws IllegalArgumentException when there are smaller count of the given item in the stock.
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
     * Id Getter
     * @return the store's id.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the store's id as a next store number.
     */
    public void setID() {
        this.ID = ++Store.storeNo;
    }

    /**
     * Name Getter
     * @return the name of the current store.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the current store by given name.
     * @param name the given name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Stock Getter
     * @return the store's stock.
     */
    public Map<InventoryItem, Integer> getStock() {
        return stock;
    }

    /**
     * Sets the store's stock by the given stock.
     * @param stock the given stock
     */
    public void setStock(Map<InventoryItem, Integer> stock) {
        this.stock = stock;
    }

    /**
     * Predefined toString() method
     * @return the representation of the store object as a string.
     */
    @Override
    public String toString() {
        return "Store { " +
                "ID: " + ID +
                ", name: '" + name + '\'' +
                ", stock: " + stock +
                " }";
    }
}