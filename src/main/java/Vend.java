import java.time.Instant;
import java.util.*;

public class Vend {

    private Stock snacks = new Stock();
    private Scanner scan;
    private List<Transaction> cancelledTransactions;
    private List<Transaction> transactions;
    private long nextId;
    private Staff staff;
    private Cashier cash;

    private Timer timer;
    private boolean timeStart = false;

    Vend() {
        scan = new Scanner(System.in);
        nextId = 000001;
        cancelledTransactions = new ArrayList<>();
        transactions = new ArrayList<>();
        staff = new Staff();
        cash = new Cashier();
        timer();
    }

    class exitMessage extends TimerTask {
        public void run() {
            System.out.println("Timed out: 15 seconds");
            System.exit(0);
        }
    }

    void timer() {
        if(timeStart) {
            timer.cancel();
        }
        timer = new Timer();
        timeStart = true;
        timer.schedule(new exitMessage(), 15000);
    }

    /**
     * Allows the program to determine if a customer or staff member is trying to use the vending machine (VM) based
     * on system input received through the scanner.
     */
    void decideUser() {
        System.out.println("Welcome! Are you a customer (C), or a staff member (S)?");
        System.out.println("If you would like to close the vending machine down, enter QUIT");
        String response = scan.nextLine();
        do {
            if (response.equals("C")) {
                timer();
                startVendingCustomer();

            } else if (response.equals("S")) {
                timer();
                startVendingStaff();
            } else if (response.equals("QUIT")) {
                System.exit(0);
            }
            System.out.println("Welcome! Are you a customer (C), or a staff member (S)?");
            System.out.println("If you would like to close the vending machine down, enter QUIT");
            response = scan.nextLine();
        } while (!response.equals("QUIT"));
    }

    /**
     * Allows the staff member to perform the necessary functions on the VM.
     */
    private void startVendingStaff() {
            System.out.println("Please enter your staff ID.");
            System.out.println("If you made a mistake and need to go back, enter CANCEL.");
        while (true) {
            timer();
            String response = scan.nextLine();
            if (cancelTransaction(response)) {
                timer();
                return;
            }
            try {
                boolean check = staff.staffMemberExists(Long.parseLong(response));
                if (check == false) {
                    System.out.println("Invalid staff ID. Try again.");
                    System.out.println("");
                    System.out.println("Please enter your staff ID.");
                    System.out.println("If you made a mistake and need to go back, enter CANCEL.");
                    continue;
                }
                System.out.println(String.format("Welcome, %s.", staff.getStaffMemberName(Long.parseLong(response))));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect ID entered, try again.");
            }
        }
        while (true) {
            System.out.println("What function would you like to perform today? Please enter ");
            System.out.println("'1' to receive the daily list of transactions,");
            System.out.println("'2' to receive the available stock,");
            System.out.println("'3' to receive the list of cancelled transactions.");
            System.out.println("'CANCEL' to return to home menu.");
            timer();
            String option = scan.nextLine();
            if (option.equals("1")) {
                timer();
                System.out.println("Daily transactions");
                for (Transaction transaction: transactions) {
                    System.out.println(transaction);
                }
                System.out.println();
            } else if (option.equals("2")) {
                timer();
               snacks.stockTake();
            } else if (option.equals("3")) {
                timer();
                System.out.println("Cancelled transactions");
                for (Transaction transaction: cancelledTransactions) {
                    System.out.println(transaction);
                }
                System.out.println();
            } else if (option.equals("CANCEL")) {
                timer();
                break;
            }
        }
    }
    /**
     * Allows the customer to perform the necessary functions on the VM.
     */
    private void startVendingCustomer() {
        while (true) {
            this.snacks.viewSnackOptions();
            System.out.println();
            System.out.println("If at any time before purchase, you would like to cancel your transaction, enter CANCEL.");
            System.out.println("Please enter item name or unique code you wish to purchase.");
            String response = scan.nextLine();
            if (cancelTransaction(response)) {
                continue;
            }
            String itemToPurchase = enterItem(response);
            if (itemToPurchase == null) {
                System.out.println("You have entered an invalid name or code, please start again or type QUIT to finish using the vending machine.");
                String response2 = scan.nextLine();
                if (response2.equals("QUIT")) {
                    System.exit(0);
                } //otherwise continue
                continue;
            }
            timer();
            System.out.println("Please enter how many of this item you would like to purchase.");
            response = scan.nextLine();
            if (cancelTransaction(response)) {
                timer();
                continue;
            }
            timer();
            int amountToPurchase = enterAmount(Integer.parseInt(response), itemToPurchase);
            if (amountToPurchase < 0) {
                System.out.println("You have entered an invalid amount, please start again.");
                continue;
            }
            timer();
            System.out.println("Confirming purchase of " + amountToPurchase + " x " + itemToPurchase + "'s.");
            System.out.println("If you wish to cancel this transaction, enter CANCEL. Otherwise, ignore and return");
            String confirmation = scan.nextLine();
            if (cancelTransaction(confirmation)) {
                timer();
                System.out.println("You have chosen to cancel your transaction, you will be returned to the main " +
                        "menu and may start again.");
                createTransaction(itemToPurchase, amountToPurchase, 0.00, 0.00, true);
                continue;
            } else {
                timer();
                double payable = calculatePayable(itemToPurchase, amountToPurchase);
                cash.initNewPayment(payable);
                System.out.println(String.format("Please enter amount payable: $%.2f", payable));
                System.out.println("You may enter coins: 10c, 20c, 50c, $1, $2; or notes: $5, $10, $20.");
                double payment = 0;
                while (payable > payment + 0.01) {
                    timer();
                    System.out.println(String.format("Amount still owing: $%.2f", (payable - payment)));
                    String pay = scan.nextLine();
                    if (cancelTransaction(pay)) {
                        timer();
                        createTransaction(itemToPurchase, amountToPurchase, payment, payment, true);
                        System.out.println(String.format("Sorry to see you go, please collect the money you inserted" +
                                ": $%.2f", (cash.currentlyInsertedMoney())));
                        cash.endOfPayment();
                        break;
                    }
                    double entered = receivePayment(pay);
                    if (entered < 0) {
                        timer();
                        System.out.println("That amount is not acceptable.");
                    } else {
                        timer();
                        payment += entered;
                        cash.storePayment(entered);
                    }
                }
                if (payable > payment + 0.01) {
                    timer();
                    continue;
                }
                cash.finalisePayment(payment);
                int i;
                for (i = 0; i < amountToPurchase; i++) {
                    timer();
                    System.out.println("Here is item " + (i+1) + "!");
                    snacks.remove(itemToPurchase);
                }
                double change = payment - payable;
                createTransaction(itemToPurchase, amountToPurchase, payment, change, false);
                System.out.println("Thank you, for your purchase. You will now be returned to the main menu.");
                cash.change(change);
                System.out.println(String.format("Don't forget your change of $%.2f!", change));
                cash.endOfPayment();
                timer();
            }
            System.out.println("If you have finished using the vending machine, please type YES. Otherwise, return to buy more snacks.");
            timer();
            response = scan.nextLine();
            if (response.equals("YES")) {
                break;
            }
        }
    }

    /**
     * Creates and stores transactions in one of two lists, depending on if the transaction was cancelled or completed.
     * @param itemPurchased the name of the item that was purchased.
     * @param amountPurchased how many of the items were purchased.
     * @param moneyPaid amount of money the customer paid to the VM.
     * @param changeGiven amount of change given back to the customer from the VM.
     * @param cancelled true if transaction was cancelled before completion, else false for completed transaction.
     */
    protected boolean createTransaction(String itemPurchased, int amountPurchased, double moneyPaid, double changeGiven,
                                   boolean cancelled) {
        if (cancelled) {
            cancelledTransactions.add(new Transaction(nextId, itemPurchased, amountPurchased, moneyPaid, changeGiven));
            nextId ++;
            return true;
        } else {
            transactions.add(new Transaction(nextId, itemPurchased, amountPurchased, moneyPaid, changeGiven));
            nextId++;
            return false;
        }
    }

    /**
     * Determines if the client wishes to discontinue the function it is currently performing.
     * @param confirmation a string containing the response of the client.
     * @return true if the client wishes to discontinue the function it is currently performing, else false to continue.
     */
    public boolean cancelTransaction(String confirmation) {
        return confirmation.equals("CANCEL");
    }

    /** checks if input is the code or name of an item
     *
     * @param item Snack type. Could be in unique id form or name form.
     * @return true if code, false if name
     */
    public boolean codeOrName(String item) {
        try {
            long check = Long.parseLong(item);
            return true;
        }
        catch (NumberFormatException ignored){
        }
        return false;
    }

    /**
     * Calculates the amount the customer owes to the VM for their purchase.
     * @param name name of the snack.
     * @param amount amount that individual snack costs
     * @return amountPayable amount the customer owes to the VM if they wish to purchase the amount of snacks specified.
     */
    public double calculatePayable(String name, int amount) {
        double amountPayable = 0;
        for (Product snack : snacks.getList()) {
            if (snack.getName().equals(name)) {
                amountPayable += (snack.getPrice()*amount);
                break;
            }
        }
        return amountPayable;
    }

    /**
     * Determines payment inputted by the customer.
     * @param input payment in $ or c.
     * @return amount payed by the customer in number format. If not in correct format, returns -1.
     */
    public double receivePayment(String input) {
        switch (input) {
            case "10c":
                return 0.1;
            case "20c":
                return 0.2;
            case "50c":
                return 0.5;
            case "$1":
                return 1;
            case "$2":
                return 2;
            case "$5":
                return 5;
            case "$10":
                return 10;
            case "$20":
                return 20;
            default:
                return -1;
        }
    }

    /**
     * Checks to see if what the customer has entered is an item that the VM has in stock.
     * @param itemToPurchase snack the customer wishes to purchase.
     * @return name of snack if in stock, else null
     */
    public String enterItem(String itemToPurchase) {
        if (codeOrName(itemToPurchase)) {
            for (Product snack : snacks.getList()) {
                long thisCode = Long.parseLong(itemToPurchase);
                if (snack.getCode() == thisCode) {
                    if (snack.getStockLevel() > 0) {
                        return snack.getName();
                    }
                }
            }
        }
        else {
            for (Product snack : snacks.getList()) {
                if (snack.getName().equals(itemToPurchase)) {
                    if (snack.getStockLevel() > 0) {
                        return snack.getName();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks to see if the snack entered has enough stock to provide the customer with their requested amount.
     * @param amount requested amount of the snack
     * @param name name of the snack
     * @return -1 if not in stock, else the amount they requested.
     */
    public int enterAmount(int amount, String name) {
        if (amount <= 0) {
            return -1;
        }
        for (Product snack : snacks.getList()) {
            if (snack.getName().equals(name)) {
                if (snack.getStockLevel() >= amount) {
                    return amount;
                }
            }
        }
        return -1;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}