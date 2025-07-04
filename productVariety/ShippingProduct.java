package productVariety;
import interfaces.Shippable;


public class ShippingProduct extends Product implements Shippable {
    public double weight;

    public ShippingProduct(String name, float price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}