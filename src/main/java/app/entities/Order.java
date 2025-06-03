package app.entities;
import java.util.List;
import java.util.Map;

public class Order {
    private static int count = 0;
    private int id;
    private Map<Product, Integer> products;
    private String address;
    private User user;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Float totalPrice;
    private String status = "В обработке";

    public Order(User user, Map<Product, Integer> products, String firstName, String lastName, String phoneNumber, String address, Float totalPrice) {
        this.id = ++count;
        this.user = user;
        this.products = products;
        this.address = address;
        this.totalPrice = totalPrice;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public int getId() { return id; }

    public Map<Product, Integer>  getProducts() { return products; }
    public void setProducts(Map<Product, Integer> products) {  this.products = products; }

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

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getUser() {
        return user;
    }
}
