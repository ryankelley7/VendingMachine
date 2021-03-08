public abstract class Product {

    private String name;
    private long code;
    private double price;
    private int stockLevel;

    public Product(String name, long code, double price, int stockLevel) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.stockLevel = stockLevel;
    }

    public String getName() {
        return this.name;
    }
    public long getCode() {
        return this.code;
    }
    public double getPrice() {
        return this.price;
    }
    public int getStockLevel(){
        return this.stockLevel;
    }
    public void amendStock(int increment){
        this.stockLevel += increment;
    }

}
