import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StockTest {

    @Test
    void testFill1() {
        Stock stk = new Stock();
        Chips chips = new Chips("original", 300, 3.50, 5);
        stk.fill("original", 10);
        assertEquals(5, chips.getStockLevel());
    }

    @Test
    void testFill2() {
        Stock stk = new Stock();
        Chips chips = new Chips("original", 300, 3.50, 4);
        stk.fill("original", 10);
        assertNotEquals(10, chips.getStockLevel());
    }

    @Test
    void testRemoveCode1() {
        Stock stk = new Stock();
        stk.remove("original");
        assertEquals(9, stk.getStockAmountCode(300));
    }

    @Test
    void testRemoveCode2() {
        Stock stk = new Stock();
        stk.remove(300);
        assertEquals(9, stk.getStockAmountCode(300));
    }

    @Test
    void testRemoveCode3() {
        Stock stk = new Stock();
        stk.remove(111);
        assertEquals(-1, stk.getStockAmountCode(111));
    }

    @Test
    void testRemoveName1() {
        Stock stk = new Stock();
        stk.remove("original");
        assertEquals(9, stk.getStockAmountName("original"));
    }

    @Test
    void testRemoveName2() {
        Stock stk = new Stock();
        stk.remove("original");
        assertEquals(9, stk.getStockAmountName("original"));
    }

    @Test
    void testRemoveName3() {
        Stock stk = new Stock();
        stk.remove("originall");
        assertEquals(-1, stk.getStockAmountName("originall"));
    }

    @Test
    void testStockTake1() {
        Stock stk = new Stock();
        assertEquals("\nAvailable stock:" +
                "\nwater: 10" +
                "\nsoft drink: 10" +
                "\njuice: 10" +
                "\nm&ms: 10" +
                "\nbounty: 10" +
                "\nmars: 10" +
                "\nsneakers: 10" +
                "\noriginal: 10" +
                "\nchicken: 10" +
                "\nBBQ: 10" +
                "\nsweet chillies: 10" +
                "\nsour worms: 10" +
                "\njelly beans: 10" +
                "\nlittle bears: 10" +
                "\nparty mix: 10\n\n",
                stk.stockTake());
    }

    @Test
    void testGetStockAmountCode1() {
        Stock stk = new Stock();
        assertEquals(10, stk.getStockAmountCode(100));
    }

    @Test
    void testGetStockAmountCode2() {
        Stock stk = new Stock();
        assertEquals(-1, stk.getStockAmountCode(999));
    }

    @Test
    void testGetStockAmountName1() {
        Stock stk = new Stock();
        assertEquals(10, stk.getStockAmountName("soft drink"));
    }

    @Test
    void testGetStockAmountName2() {
        Stock stk = new Stock();
        assertEquals(-1, stk.getStockAmountName("beer"));
    }

}
