import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

class VendTest {

    @Test
    void testCancelTransaction1() {
        Vend vend = new Vend();
        boolean bool = vend.cancelTransaction("Hello");
        assertFalse(bool);
    }

    @Test
    void testCancelTransaction2() {
        Vend vend = new Vend();
        boolean bool = vend.cancelTransaction("CANCEL");
        assertTrue(bool);
    }

    @Test
    void testCancelTransaction3() {
        Vend vend = new Vend();
        boolean bool = vend.cancelTransaction("cancel");
        assertFalse(bool);
    }

    @Test
    void testCodeOrName1() {
        Vend vend = new Vend();
        boolean bool = vend.codeOrName("100");
        assertTrue(bool);
    }

    @Test
    void testCodeOrName2() {
        Vend vend = new Vend();
        boolean bool = vend.codeOrName("water");
        assertFalse(bool);
    }

    @Test
    void testCalculatePayable1() {
        Vend vend = new Vend();
        double dbl = vend.calculatePayable("water", 2);
        assertEquals(4.00,dbl);
    }

    @Test
    void testCalculatePayable2() {
        Vend vend = new Vend();
        double dbl = vend.calculatePayable("water", 3);
        assertEquals(6.00,dbl);
    }

    @Test
    void testCalculatePayable3() {
        Vend vend = new Vend();
        double dbl = vend.calculatePayable("mistake", 3);
        assertEquals(0.00,dbl);
    }

    @Test
    void testReceivePayment1() {
        Vend vend = new Vend();
        double dbl = vend.receivePayment("10c");
        assertEquals(0.10, dbl);
    }
    @Test
    void testReceivePayment2() {
        Vend vend = new Vend();
        double dbl = vend.receivePayment("20c");
        assertEquals(0.20, dbl);
    }

    @Test
    void testReceivePayment3() {
        Vend vend = new Vend();
        double dbl = vend.receivePayment("50c");
        assertEquals(0.50, dbl);
    }

    @Test
    void testReceivePayment4() {
        Vend vend = new Vend();
        double dbl = vend.receivePayment("$1");
        assertEquals(1.00, dbl);
    }

    @Test
    void testReceivePayment5() {
        Vend vend = new Vend();
        double dbl = vend.receivePayment("$2");
        assertEquals(2.00, dbl);
    }

    @Test
    void testReceivePayment6() {
        Vend vend = new Vend();
        double dbl = vend.receivePayment("$5");
        assertEquals(5.00, dbl);
    }

    @Test
    void testReceivePayment7() {
        Vend vend = new Vend();
        double dbl = vend.receivePayment("$10");
        assertEquals(10.00, dbl);
    }

    @Test
    void testReceivePayment8() {
        Vend vend = new Vend();
        double dbl = vend.receivePayment("$20");
        assertEquals(20.00, dbl);
    }

    @Test
    void testReceivePayment9() {
        Vend vend = new Vend();
        double dbl = vend.receivePayment("10cccc");
        assertEquals(-1, dbl);
    }

    @Test
    void testReceivePayment10() {
        Vend vend = new Vend();
        double dbl = vend.receivePayment("$100");
        assertEquals(-1, dbl);
    }

    @Test
    void testEnterItem1() {
        Vend vend = new Vend();
        String str = vend.enterItem("water");
        assertEquals("water", str);
    }

    @Test
    void testEnterItem2() {
        Vend vend = new Vend();
        String str = vend.enterItem("waterr");
        assertNull(str);
    }

    @Test
    void testEnterItem3() {
        Vend vend = new Vend();
        String str = vend.enterItem("100");
        assertEquals("water", str);
    }

    @Test
    void testEnterItem4() {
        Vend vend = new Vend();
        String str = vend.enterItem("10001");
        assertNull(str);
    }

    @Test
    void testEnterAmount1() {
        Vend vend = new Vend();
        int i = vend.enterAmount(2, "water");
        assertEquals(2, i);
    }

    @Test
    void testEnterAmount2() {
        Vend vend = new Vend();
        int i = vend.enterAmount(9, "water");
        assertEquals(9, i);
    }

    @Test
    void testEnterAmount3() {
        Vend vend = new Vend();
        int i = vend.enterAmount(10, "water");
        assertEquals(10, i);
    }

    @Test
    void testEnterAmount4() {
        Vend vend = new Vend();
        int i = vend.enterAmount(11, "water");
        assertEquals(-1, i);
    }

    @Test
    void testEnterAmount5() {
        Vend vend = new Vend();
        int i = vend.enterAmount(0, "water");
        assertEquals(-1, i);
    }

    @Test
    void testEnterAmount6() {
        Vend vend = new Vend();
        int i = vend.enterAmount(-1, "water");
        assertEquals(-1, i);
    }

    @Test
    void vendTest1() {
        Vend test = new Vend();
        assertFalse(test.createTransaction("Caramilk", 1, 3.0, 0.0,
                false));
        assertEquals( 000001, test.getTransactions().get(0).getTransactionID());
        assertEquals("Caramilk", test.getTransactions().get(0).getItemName());
        assertEquals(1, test.getTransactions().get(0).getItemsSold());
        assertEquals(0.0, test.getTransactions().get(0).getChangeGiven());
        assertEquals(3.0, test.getTransactions().get(0).getMoneyPaid());
        assertEquals(dateAndTime(), test.getTransactions().get(0).getDateTime());
    }

    private String dateAndTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return(myDateObj.format(myFormatObj));
    }

}
