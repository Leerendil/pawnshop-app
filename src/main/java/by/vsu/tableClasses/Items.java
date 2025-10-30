package by.vsu.tableClasses;

public class Items {
    private int item_id;
    private String item_name;
    private double item_pledge_amount;
    private String item_status;
    private int fk_client_id;
    private int fk_category_id;

    public Items() {}

    public Items(int item_id, String item_name, double item_pledge_amount, String item_status, int fk_client_id, int fk_category_id) {
        setItem_id(item_id);
        setItem_name(item_name);
        setItem_pledge_amount(item_pledge_amount);
        setItem_status(item_status);
        setFk_client_id(fk_client_id);
        setFk_category_id(fk_category_id);
    }

    public Items(String item_name, double item_pledge_amount, String item_status, int fk_client_id, int fk_category_id) {
        setItem_name(item_name);
        setItem_pledge_amount(item_pledge_amount);
        setItem_status(item_status);
        setFk_client_id(fk_client_id);
        setFk_category_id(fk_category_id);
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_pledge_amount() {
        return item_pledge_amount;
    }

    public void setItem_pledge_amount(double item_pledge_amount) {
        this.item_pledge_amount = item_pledge_amount;
    }

    public String getItem_status() {
        return item_status;
    }
    public void setItem_status(String item_status) {
        this.item_status = item_status;
    }

    public int getFk_client_id() {
        return fk_client_id;
    }

    public void setFk_client_id(int fk_client_id) {
        this.fk_client_id = fk_client_id;
    }

    public int getFk_category_id() {
        return fk_category_id;
    }

    public void setFk_category_id(int fk_category_id) {
        this.fk_category_id = fk_category_id;
    }

    @Override
    public String toString() {
        return "Item: " +
               "\nitem id: " + item_id +
               "\nitem name: " + item_name+
               "\nitem pledge amount: " + item_pledge_amount +
               "\nitem status: " + item_status +
               "\nclient id(fk): " + fk_client_id +
               "\ncategory id(fk): " + fk_category_id;
    }
}
