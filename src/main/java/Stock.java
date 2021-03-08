import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Stock {
    private ArrayList<Product> stock = new ArrayList<>();
    private static final int MAXCAPACITY = 10;
    public Stock() {
        this.stock.add(new Drink("water", 100, 2.00, 10));
        this.stock.add(new Drink("soft drink", 101, 2.50, 10));
        this.stock.add(new Drink("juice", 102, 3.00, 10));
        this.stock.add(new Chocolate("m&ms", 200, 3.00, 10));
        this.stock.add(new Chocolate("bounty", 201, 3.20, 10));
        this.stock.add(new Chocolate("mars", 202, 3.20, 10));
        this.stock.add(new Chocolate("sneakers", 203, 3.40, 10));
        this.stock.add(new Chips("original", 300, 3.50, 10));
        this.stock.add(new Chips("chicken", 301, 3.70, 10));
        this.stock.add(new Chips("BBQ", 302, 3.70, 10));
        this.stock.add(new Chips("sweet chillies", 303, 4.00, 10));
        this.stock.add(new Lollies("sour worms", 400, 1.80, 10));
        this.stock.add(new Lollies("jelly beans", 401, 1.50, 10));
        this.stock.add(new Lollies("little bears", 402, 1.70, 10));
        this.stock.add(new Lollies("party mix", 403, 2.00, 10));
    }

    public void viewSnackOptions() {
        System.out.println("Snacks available:");
        for(Product snack : stock) {
            System.out.format("%-20s%-6d$%-10.2f", snack.getName(), snack.getCode(), snack.getPrice());
            System.out.println();
        }
    }

    public String stockTake(){
        String output = "\nAvailable stock:\n";
        System.out.println();
        System.out.println("Available stock:");
        for (Product snack : stock) {
            output += snack.getName() + ": " + snack.getStockLevel() + "\n";
            System.out.println(snack.getName() + ": " + snack.getStockLevel());
        }
        output += "\n";
        System.out.println();
        return output;
    }

    public void fill(String snackName, int amount) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateTime = myDateObj.format(myFormatObj);
        for (Product snack : stock) {
            if (snack.getName().equals(snackName)) {
                if (snack.getStockLevel() + amount <= MAXCAPACITY) {
                    snack.amendStock(amount);
                    System.out.println("Successfully filled stock for " + snackName + " at " + dateTime + ".");
                }
            }
        }
    }
    public void remove(String snackName) {
        for (Product snack : stock) {
            if (snack.getName().equals(snackName)) {
                snack.amendStock(-1);
            }
        }
    }
    public void remove(long snackCode) {
        for (Product snack : stock) {
            if (snack.getCode() == (snackCode)) {
                snack.amendStock(-1);
            }
        }
    }

    public ArrayList<Product> getList() {
        return stock;
    }

    public int getStockAmountCode(long code) {
        for (Product snack : stock) {
            if (snack.getCode() == code) {
                return snack.getStockLevel();
            }
        }
        return -1;
    }

    public int getStockAmountName(String name) {
        for (Product snack : stock) {
            if (snack.getName().equals(name)) {
                return snack.getStockLevel();
            }
        }
        return -1;
    }
}
