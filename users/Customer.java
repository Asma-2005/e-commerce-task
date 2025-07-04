package users;

public class Customer {
    private String id;
    private float money;

    public Customer(String id, float money) {
        this.id = id;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public float getMoney() {
        return money;
    }

    public void spent(float amount) {
        money -= amount;
    }
}