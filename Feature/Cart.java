package Feature;

import productVariety.*;
import interfaces.Shippable;
import users.Customer;
import exceptions.MyErr;

import java.util.*;

public class Cart {
    private Map<Product, Integer> items = new LinkedHashMap<>();
    private float subTotal = 0;
    private float shippingFees = 0;

    public void add(Product p1, int amount) throws MyErr {
        if (amount > p1.quantity) {
            throw new MyErr("not enough stock for " + p1.getName() + " available: " + p1.quantity);
        }
        if (p1 instanceof ExpireProduct && ((ExpireProduct) p1).expiryDate.before(new Date())) {
            throw new MyErr(p1.getName() + " is expired XX");
        }
        p1.quantity -= amount;
        items.put(p1, items.getOrDefault(p1, 0) + amount);
    }

    public void checkOut(Customer customer) throws MyErr {
        if (items.isEmpty()) throw new MyErr("ur cart is empty :(");

        List<Shippable> toShip = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int quant = entry.getValue();
            subTotal += p.getPrice() * quant;
            if (p instanceof Shippable) {
                for (int i = 0; i < quant; i++) {
                    toShip.add((Shippable) p); //casting the abstract
                }
            }
        }

        //shipping fees for 0.5 kg or less is 15 egp, less than 10 is 30 egp more is 50 egp
        double totalWeight = toShip.stream().mapToDouble(Shippable::getWeight).sum(); //stream summation?
        if (totalWeight <= 0.5) shippingFees = 15;
        else if (totalWeight < 10) shippingFees = 30;
        else shippingFees = 50;

        float total = subTotal + shippingFees;
        if (customer.getMoney() < total)
            throw new MyErr("Insufficient balance! Required: " + total + ", Available: " + customer.getMoney());

        if (!toShip.isEmpty()) ShippingService.ship(toShip);

        customer.spent(total);

        System.out.println("\n** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            System.out.println(entry.getValue() + "x " + entry.getKey().getName() + " " + entry.getKey().getPrice() * entry.getValue());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal: " + subTotal);
        System.out.println("shipping: " + shippingFees);
        System.out.println("amount paid: " + total);
        System.out.println("remain balance: " + customer.getMoney());
    }
}