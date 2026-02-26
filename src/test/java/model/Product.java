package model;

public class Product {
    private String name;
    private String url;
    private String image;
    private String category;
    private double price;
    private int salesCount;
    private boolean inStock;

    public Product(String name, String url, String image, String category, double price, int salesCount, boolean inStock) {
        this.name = name;
        this.url = url;
        this.image = image;
        this.category = category;
        this.price = price;
        this.salesCount = salesCount;
        this.inStock = inStock;
    }

    public String getName() { return name; }
    public String getUrl() { return url; }
    public String getImage() { return image; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getSalesCount() { return salesCount; }
    public boolean getInStock() { return inStock; }
}