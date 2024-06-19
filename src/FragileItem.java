public class FragileItem extends InventoryItem implements Breakable {
    private double weight;
    private boolean isBroken;
    public FragileItem() {
        super();
        this.setCategory(Category.FRAGILE);
        this.isBroken = false;
    }

    public FragileItem(int quantity, double price) {
        super(quantity, price);
        this.setCategory(Category.FRAGILE);
        this.isBroken = false;
    }

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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws IllegalArgumentException {
        if(0.00 <= weight) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Weight should be positive floating point number.");
        }
    }

    @Override
    public boolean isBreakable() {
        return !this.isBroken;
    }

    @Override
    public void handleBreakage() {
        if(this.isBreakable()) {
            this.isBroken = true;
        }
    }
}
