import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {
    //need to fix this formatting?
    @Test
    void toStringTest() {
        Transaction transaction = new Transaction(000001, "original", 1, 5,  1.50);
        String dat = dateAndTime();
        String str1 = "Transaction ID: 1, Date and Time: " + dat;
        String str2 = ", Items Sold: 1, Amount Paid: 5.0, Change Returned: 1.5";
        String str3 = str1 + str2;
        assertEquals(str3, transaction.toString());
    }

    private String dateAndTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return(myDateObj.format(myFormatObj));
    }

    @Test
    void testGetTransactionId1() {
        Transaction trans = new Transaction(1, "water", 3, 6.00, 0.00);
        assertEquals(1, trans.getTransactionID());
    }

    @Test
    void testGetItemName1() {
        Transaction trans = new Transaction(1, "water", 3, 6.00, 0.00);
        assertEquals("water", trans.getItemName());
    }

    @Test
    void testGetItemsSold1() {
        Transaction trans = new Transaction(1, "water", 3, 6.00, 0.00);
        assertEquals(3, trans.getItemsSold());
    }

    @Test
    void testGetMoneyPaid1() {
        Transaction trans = new Transaction(1, "water", 3, 6.00, 0.00);
        assertEquals(6.00, trans.getMoneyPaid());
    }

    @Test
    void testGetChangeGiven1() {
        Transaction trans = new Transaction(1, "water", 3, 6.00, 0.00);
        assertEquals(0.00, trans.getChangeGiven());
    }
}
