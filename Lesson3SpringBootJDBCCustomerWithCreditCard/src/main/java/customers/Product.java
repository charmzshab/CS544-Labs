package customers;

public class Product {private int productNumber;
    private String name;
    private Double price;
    private Supplier supplier;

    public Product(int productNumber, String name, Double price) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNumber=" + productNumber +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", supplier=" + supplier +
                '}';
    }

    public int getProductNumber() {
        return productNumber;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(Double price) {
        this.price = price;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }
}
