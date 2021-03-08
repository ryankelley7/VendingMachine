public class Cashier {

    private double bank;
    private double payable;
    private double currentPayment;
    public Cashier() { this.bank = 100.00; }
    double moneyInBank() { return bank; }
    void initNewPayment(double amount) { payable = amount; }
    void storePayment(double amount) { currentPayment += amount; }
    void finalisePayment(double amount) { bank += amount; }
    double currentlyInsertedMoney() { return currentPayment; }
    void change(double amount) { bank -= amount; }
    double getPayable() { return payable; }
    void endOfPayment() {
        currentPayment = 0;
        payable = 0;
    }
}
