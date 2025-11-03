package by.vsu.tableClasses;

public class Transactions {
    private int transactionId;
    private String transactionType;
    private double amount;
    private int transactionDate;
    private int fkEmployeeId;
    private int fkItemId;

    public Transactions() {}

    public Transactions(int transactionId, String transactionName, double amount, int transactionDate, int fkEmployeeId, int fkItemId) {
        setTransactionId(transactionId);
        setTransactionType(transactionName);
        setAmount(amount);
        setTransactionDate(transactionDate);
        setFkEmployeeId(fkEmployeeId);
        setFkItemId(fkItemId);

    }
    public Transactions(String transactionName, double amount, int transactionDate, int fkEmployeeId, int fkItemId) {
        setTransactionType(transactionName);
        setAmount(amount);
        setTransactionDate(transactionDate);
        setFkEmployeeId(fkEmployeeId);
        setFkItemId(fkItemId);
    }

    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public int getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(int transactionDate) {
        this.transactionDate = transactionDate;
    }
    public int getFkEmployeeId() {
        return fkEmployeeId;
    }
    public void setFkEmployeeId(int fkEmployeeId) {
        this.fkEmployeeId = fkEmployeeId;
    }
    public int getFkItemId() {
        return fkItemId;
    }
    public void setFkItemId(int fkItemId) {
        this.fkItemId = fkItemId;
    }

    @Override
    public String toString() {
        return "Transaction: " +
               "\nid: " + transactionId +
               "\nname: " + transactionType +
               "\namount: " + amount +
               "\ndate: " + transactionDate +
               "\nfk employee id: " + fkEmployeeId +
               "\nfk item id: " + fkItemId;
    }

}
