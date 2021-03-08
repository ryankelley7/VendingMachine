import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffTest {
    @Test
    void testStaffName1() {
        Staff staff = new Staff();
        String staffername = staff.getStaffMemberName((long) 123456789);
        assertEquals("Willy Wonka", staffername);
    }

    @Test
    void testStaffExists1() {
        Staff staff = new Staff();
        boolean staffername = staff.staffMemberExists((long) 987654321);
        assertEquals(true, staffername);
    }

    @Test
    void testStaffExists2() {
        Staff staff = new Staff();
        boolean staffername = staff.staffMemberExists((long) 135792468);
        assertEquals(true, staffername);
    }
}
