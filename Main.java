import productVariety.*;
import users.Customer;
import Feature.Cart;
import exceptions.MyErr;

import java.util.Date;


    public class Main {
        public static void main(String[] args) {
            try {
                ShippingProduct cheese = new ShippingProduct("Cheese", 100, 5, 0.2);
                ShippingProduct tv = new ShippingProduct("TV", 300, 3, 0.9);
                NonExpiredProduct scratch = new NonExpiredProduct("ScratchCard", 50, 10);
                ExpireProduct biscuits = new ExpireProduct("Biscuits", 150, 2, new Date(System.currentTimeMillis() + 100000));

                Customer customer = new Customer("player456", 10000);
                Cart cart = new Cart();

                cart.add(cheese, 2);
                cart.add(tv, 1);
                cart.add(biscuits, 1);
                cart.add(scratch, 1);

                cart.checkOut(customer);

            } catch (MyErr e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

