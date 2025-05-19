package app.entities;

public class Product {
    private static int count = 0;
    private int id;
    private String name;
    private String description;
    private Float prodPrice;

    public Product(String name, String description, Float prodPrice) {
        this.id = ++count;
        this.name = name;
        this.description = description;
        this.prodPrice = prodPrice;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Float getProdPrice() {
        return prodPrice;
    }
    public void setProdPrice(Float prodPrice) {
        this.prodPrice = prodPrice;
    }

    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }
}
