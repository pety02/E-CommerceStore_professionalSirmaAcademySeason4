import java.io.Serializable;

public abstract class AbstractItem implements Item, Categorizable, Sellable, Serializable {
    @Override
    public String getDetails() {
        return String.format("Abstract Item Description:\n{\n\tcategory: \"%s\",\n\tbreakable: %b,\n\tperishable: %b,\n\tprice: %.2f\n}",
                Category.ELECTRONICS.name(), true, false, 120.50);
    }
}