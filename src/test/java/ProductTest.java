import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductTest {
    @Test
    void testAmendStock1() {
        Chips chips = new Chips("original", 300, 3.50, 10);
        int testInc = 5;
        chips.amendStock(testInc);
        assertEquals(15, chips.getStockLevel());;
    }

    @Test
    void testAmendStock2() {
        Chips chips = new Chips("original", 300, 3.50, 10);
        int testInc = -5;
        chips.amendStock(testInc);
        assertEquals(5, chips.getStockLevel());
    }

    @Test
    void testAmendStock3() {
        Chips chips = new Chips("original", 300, 3.50, 10);
        int testInc = -15;
        chips.amendStock(testInc);
        assertEquals(-5, chips.getStockLevel());
    }

    @Test
    void testGetName1() {
        Chips chips = new Chips("original", 300, 3.50, 10);
        assertEquals("original", chips.getName());
    }

    @Test
    void testGetCode1() {
        Chips chips = new Chips("original", 300, 3.50, 10);
        assertEquals(300, chips.getCode());
    }

    @Test
    void testGetPrice1() {
        Chips chips = new Chips("original", 300, 3.50, 10);
        assertEquals(3.50, chips.getPrice());
    }

    @Test
    void testGetStockLevel1() {
        Chips chips = new Chips("original", 300, 3.50, 10);
        assertEquals(10, chips.getStockLevel());
    }

}
