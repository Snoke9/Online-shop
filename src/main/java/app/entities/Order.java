package app.entities;
import java.util.List;

public class Order {
    private static int count = 0;
    private int id;
    private List<Product> products;
    private String address;
    private User user;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Float totalPrice;
    private boolean completed;

    public Order(User user, List<Product> products, String firstName, String lastName, String phoneNumber, String address, Float totalPrice) {
        this.id = ++count;
        this.user = user;
        this.products = products;
        this.address = address;
        this.totalPrice = totalPrice;
        this.completed = false;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public int getId() { return id; }

    public List<app.entities.Product> getProducts() { return products; }
    public void setProducts(List<app.entities.Product> products) {  this.products = products; }

    public String getAddress() { return address; }
    public void setAddress(String address) {
        this.address = address;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isCompleted() { return completed; }
    public void complete() { this.completed = true; }

    public User getUser() {
        return user;
    }
}
