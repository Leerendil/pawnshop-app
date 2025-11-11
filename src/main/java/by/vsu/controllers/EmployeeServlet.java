package by.vsu.controllers;

import by.vsu.repository.EmployeesRepository;
import by.vsu.service.EmployeeService;
import by.vsu.tableClasses.Employees;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService( new EmployeesRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        if (path == null) {path="";}

        resp.setContentType("text/html; charset=utf-8");

        switch (path) {
            case "/getAll" -> getAll(resp);
            case "/getById" -> getById(req, resp);
            case "/delete" -> delete(req, resp);
            default -> resp.getWriter().println("<h1>No such path</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        if (path == null) {path="";}

        resp.setContentType("text/html; charset=utf-8");

        if (path.equals("/add")) {
            add(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        if (path == null) {path="";}
        resp.setContentType("text/html; charset=utf-8");

        if (path.equals("/update")) {
            update(req, resp);
        }
    }

    private void getAll(HttpServletResponse resp) throws IOException {
        var employees = employeeService.getAll();
        resp.getWriter().println("<h1>Employees list:</h1>");
        for (var employee : employees) {
            resp.getWriter().println(employee + "<br>");
        }
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            Employees employee = employeeService.getById(id);

            resp.getWriter().println(employee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            employeeService.delete(id);

            resp.getWriter().println("<h1>Employee deleted</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            String firstName = req.getParameter("first_name");
            String lastName = req.getParameter("last_name");
            String roleOfEmployee = req.getParameter("role_of_employee");

            employeeService.add(new Employees(firstName, lastName, roleOfEmployee));

            resp.getWriter().println("<h1>Employee added</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            String firstName = req.getParameter("first_name");
            String lastName = req.getParameter("last_name");
            String roleOfEmployee = req.getParameter("role_of_employee");

            employeeService.update(new Employees(id, firstName, lastName, roleOfEmployee));

            resp.getWriter().println("<h1>Employee updated</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
