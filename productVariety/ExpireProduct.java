package productVariety;

import java.util.Date;

public class ExpireProduct extends Product {
    public Date expiryDate;

    public ExpireProduct(String name, float price, int quantity, Date expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }
}