package by.vsu.tableClasses;

public class Clients {
    private int client_id;
    private int passport_number;
    private String first_name;
    private String last_name;
    private int phone_number;

    public Clients() {}

    public Clients(int client_id, int passport_number, String first_name, String last_name, int phone_number) {
        setClient_id(client_id);
        setPassport_number(passport_number);
        setFirst_name(first_name);
        setLast_name(last_name);
        setPhone_number(phone_number);
    }

    public Clients(int passport_number, String first_name, String last_name, int phone_number) {
        setPassport_number(passport_number);
        setFirst_name(first_name);
        setLast_name(last_name);
        setPhone_number(phone_number);
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(int passport_number) {
        this.passport_number = passport_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "\nClient: " + client_id +
               ", passport number " + passport_number +
               ", first name " + first_name +
               ", last name " + last_name +
               ", phone number " + phone_number;
    }

}
