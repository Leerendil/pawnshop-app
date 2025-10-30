package by.vsu;


import by.vsu.RepositoryClasses.CategoriesRepository;
import by.vsu.RepositoryClasses.ClientsRepository;
import by.vsu.RepositoryClasses.EmployeesRepository;
import by.vsu.RepositoryClasses.ItemsRepository;
import by.vsu.tableClasses.Categories;
import by.vsu.tableClasses.Clients;
import by.vsu.tableClasses.Employees;
import by.vsu.tableClasses.Items;


public class Main {
    public static void main(String[] args) {

        ClientsRepository clientsRepository = new ClientsRepository();
        CategoriesRepository categoriesRepository = new CategoriesRepository();
        ItemsRepository itemsRepository = new ItemsRepository();
        EmployeesRepository employeesRepository = new EmployeesRepository();

        Clients client = new Clients();
        Categories category = new Categories();
        Items item = new Items();
        Employees employee = new Employees();

        for (int i = 0; i < categoriesRepository.getAll().size(); i++) {
            System.out.println(categoriesRepository.getAll().get(i) + "\n");
        }
    }
}