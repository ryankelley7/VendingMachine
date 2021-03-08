import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private long transactionID;
    private String itemName;
    private String dateTime;
    private int itemsSold;
    private double moneyPaid;
    private double changeGiven;

    public Transaction(long id, String itemName, int itemsSold, double moneyPaid, double changeGiven) {
        this.transactionID = id;
        this.itemName = itemName;
        this.dateTime = dateAndTime();
        this.itemsSold = itemsSold;
        this.moneyPaid = moneyPaid;
        this.changeGiven = changeGiven;
    }

    protected String dateAndTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return(myDateObj.format(myFormatObj));
    }

    @Override
    public String toString() {
        return "Transaction ID: " + this.transactionID + ", Date and Time: " + this.dateTime +
                ", Items Sold: " + this.itemsSold + ", Amount Paid: " + this.moneyPaid + ", Change Returned: "
                + changeGiven;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getItemsSold() {
        return itemsSold;
    }

    public double getMoneyPaid() {
        return moneyPaid;
    }

    public double getChangeGiven() {
        return changeGiven;
    }
}
