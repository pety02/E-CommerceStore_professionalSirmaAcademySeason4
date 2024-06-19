import java.time.LocalDateTime;

public class GroceryItem extends InventoryItem implements Perishable {
    private LocalDateTime expireTill;

    public GroceryItem() {
        super();
        this.setCategory(Category.GROCERY);
    }

    public GroceryItem(int quantity, double price) {
        super(quantity, price);
        this.setCategory(Category.GROCERY);
    }

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

    @Override
    public boolean isPerishable() {
        return expireTill.isBefore(LocalDateTime.now());
    }

    @Override
    public void handleExpiration() {
        if(this.isPerishable()) {
            this.setQuantity(0);
        }
    }

    public LocalDateTime getExpireTill() {
        return expireTill;
    }

    public void setExpireTill(LocalDateTime expireTill) throws IllegalArgumentException {
        if(LocalDateTime.now().isBefore(expireTill) || expireTill == null) {
            this.expireTill = expireTill;
        } else {
            throw new IllegalArgumentException("Expiration date should be after or equal to current date.");
        }
    }
}