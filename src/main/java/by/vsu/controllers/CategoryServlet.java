package by.vsu.controllers;

import by.vsu.repository.CategoriesRepository;
import by.vsu.service.CategoryService;
import by.vsu.tableClasses.Categories;
import by.vsu.tableClasses.Clients;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class CategoryServlet extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() {
        categoryService = new CategoryService(new CategoriesRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {pathInfo = "";}

        resp.setContentType("text/html; charset=utf-8");

        switch (pathInfo) {
            case "/getAll" -> getAll(resp);
            case "/getById" -> getById(req, resp);
            case "/delete" -> delete(req, resp);
            default -> resp.getWriter().println("No such path!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null) {pathInfo = "";}

        resp.setContentType("text/html; charset=utf-8");

        if (pathInfo.equals("/add")) {
            add(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {pathInfo = "";}

        resp.setContentType("text/html; charset=utf-8");

        if (pathInfo.equals("/update")) {
            update(req, resp);
        }
    }

    private void getAll(HttpServletResponse resp) {
        try {
            var categories = categoryService.getAll();
            resp.getWriter().println("<h2>Categories list:</h2>");
            for (var category : categories) {
                resp.getWriter().println(category.toString() + "<br>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Categories category = categoryService.getById(id);
            if (category != null) {
                resp.getWriter().println("<h2>Category:</h2>");
                resp.getWriter().println(category);
            } else {
                resp.getWriter().println("<h3>No client with such id!</h3>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
        int id = Integer.parseInt(req.getParameter("id"));

        categoryService.delete(id);

        resp.getWriter().println("<h3>Category deleted!</h3>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        try {

            String name = req.getParameter("name");

            categoryService.add(new Categories(name));

            resp.getWriter().println("<h3>Category added!</h3>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");

            categoryService.update(new Categories(id, name));

            resp.getWriter().println("<h3>Category updated!</h3>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
