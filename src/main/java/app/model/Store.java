package app.model;

import app.entities.Order;
import app.entities.Product;
import app.entities.User;

import java.util.*;

public class Store {
    private static List<Product> products = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void deleteProduct(int prodId) {
        products.removeIf(prod -> prod.getId() == prodId);
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void deleteUser(int userId) {
        users.removeIf(user -> user.getId() == userId);
    }

    public Product getProduct (int id) {
        for (Product prod : products) {
            if (prod.getId() == id) {
                return prod;
            }
        }
        return null;
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static void createOrder(Order order) {
        orders.add(order);
    }

    public static void deleteOrder(int id) {
        orders.removeIf(order -> order.getId() == id);
    }

    public Order getOrder(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void cancelOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                order.setStatus("Отменён");
                break;
            }
        }
    }

    public static void updateStatus(int id, String status) {
        for (Order order : orders) {
            if (order.getId() == id) {
                order.setStatus(status);
                break;
            }
        }
    }
}
