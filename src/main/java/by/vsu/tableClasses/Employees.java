package by.vsu.tableClasses;

public class Employees {
    private int employee_id;
    private String first_name;
    private String last_name;
    private String role_of_employee;

    public Employees() {}

    public Employees(int employee_id, String first_name, String last_name, String role_of_employee) {
        setEmployee_id(employee_id);
        setFirst_name(first_name);
        setLast_name(last_name);
        setRole_of_employee(role_of_employee);
    }

    public Employees(String first_name, String last_name, String role_of_employee) {
        setFirst_name(first_name);
        setLast_name(last_name);
        setRole_of_employee(role_of_employee);
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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

    public String getRole_of_employee() {
        return role_of_employee;
    }

    public void setRole_of_employee(String role_of_employee) {
        this.role_of_employee = role_of_employee;
    }

    @Override
    public String toString() {
        return "Emplotyee " + employee_id +
               ", " + first_name + " " + last_name +
               ", " + role_of_employee + ";";
    }
}
