package by.vsu.service;

import by.vsu.repository.EmployeesRepository;
import by.vsu.tableClasses.Employees;

public class EmployeeService extends Service<Employees>{
    public EmployeeService(EmployeesRepository employeesRepository) {
        super(employeesRepository);
    }
}
