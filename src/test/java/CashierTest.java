import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CashierTest {
    @Test
    void testStorePayment1() {
        Cashier cash = new Cashier();
        int testMoney = 5;
        cash.storePayment(testMoney);
        assertEquals(5, cash.currentlyInsertedMoney());
    }

    @Test
    void testFinalisePayment1() {
        Cashier cash = new Cashier();
        int testMoney = 5;
        cash.finalisePayment(testMoney);
        assertEquals(105, cash.moneyInBank());
    }

    @Test
    void testChange1() {
        Cashier cash = new Cashier();
        int testMoney = 5;
        cash.change(testMoney);
        assertEquals(95, cash.moneyInBank());
    }

    @Test
    void testInitNewPayment1() {
        Cashier cash = new Cashier();
        int testMoney = 5;
        cash.initNewPayment(testMoney);
        assertEquals(5, cash.getPayable());
    }

    @Test
    void testEndOfPayment1() {
        Cashier cash = new Cashier();
        cash.endOfPayment();
        boolean bool = false;
        if (cash.getPayable() == 0 && cash.currentlyInsertedMoney() == 0) {
             bool = true;
        }
        assertTrue(bool);
    }
}
