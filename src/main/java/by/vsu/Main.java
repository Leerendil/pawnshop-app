package by.vsu;


import by.vsu.RepositoryClasses.*;
import by.vsu.tableClasses.*;


public class Main {
    public static void main(String[] args) {

        ClientsRepository clientsRepository = new ClientsRepository();
        CategoriesRepository categoriesRepository = new CategoriesRepository();
        ItemsRepository itemsRepository = new ItemsRepository();
        EmployeesRepository employeesRepository = new EmployeesRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        Clients client = new Clients();
        Categories category = new Categories();
        Items item = new Items();
        Employees employee = new Employees();

    }
}


