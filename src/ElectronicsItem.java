public class ElectronicsItem extends FragileItem {
    private ElectronicsType type;

    public ElectronicsItem() {
        super();
    }

    public ElectronicsItem(int quantity, double price) {
        super(quantity, price);
        this.setCategory(Category.ELECTRONICS);
    }

    public ElectronicsItem(int quantity, double price, double weight) {
        super(quantity, price, weight);
        this.setCategory(Category.ELECTRONICS);
    }


    public ElectronicsItem(int quantity, double price, double weight, ElectronicsType type) {
        super(quantity, price, weight);
        this.setCategory(Category.ELECTRONICS);
        this.setType(type);
    }

    public ElectronicsType getType() {
        return type;
    }

    public void setType(ElectronicsType type) {
        this.type = type;
    }
}