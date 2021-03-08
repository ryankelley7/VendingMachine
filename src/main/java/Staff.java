import java.util.HashMap;

public class Staff {

    private HashMap<Long, String> staffList;

    Staff() {
        staffList = new HashMap<>();
        staffList.put((long) 123456789, "Willy Wonka");
        staffList.put((long) 987654321, "Oompa Loompa");
        staffList.put((long) 135792468, "Harry Potter");
    }

    boolean staffMemberExists(Long id) {

        return staffList.containsKey(id);
    }

    String getStaffMemberName(Long id) {

        return staffList.get(id);
    }

}
