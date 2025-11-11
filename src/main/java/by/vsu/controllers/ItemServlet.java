package by.vsu.controllers;

import by.vsu.repository.ItemsRepository;
import by.vsu.service.ItemService;
import by.vsu.tableClasses.Items;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ItemServlet extends HttpServlet {
    private ItemService itemService;

    @Override
    public void init() {
        itemService = new ItemService(new ItemsRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String path = req.getPathInfo();
        if (path == null) {path = "";}

        resp.setContentType("text/html; charset=utf-8");

        switch (path) {
            case "/getAll" -> getAll(resp);
            case "/getById" -> getById(req, resp);
            case "/delete" -> delete(req, resp);
            default -> resp.getWriter().println("No such path!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getPathInfo();
        if (path == null) {path = "";}

        resp.setContentType("text/html; charset=utf-8");

        if (path.equals("/add")) {
            add(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getPathInfo();
        if (path == null) {path = "";}

        resp.setContentType("text/html; charset=utf-8");

        if (path.equals("/update")) {
            update(req, resp);
        }
    }

    private void getAll(HttpServletResponse resp) {
        try {
            var items = itemService.getAll();
            resp.getWriter().println("<h2>Items list:</h2>");
            for (var item : items) {
                resp.getWriter().println(item.toString().replace("\n", "<br>") + "<br><br>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Items item = itemService.getById(id);
            if (item != null) {
                resp.getWriter().println("<h2>Item:</h2>");
                resp.getWriter().println(item.toString().replace("\n", "<br>"));
            } else {
                resp.getWriter().println("<h3>No item with such id!</h3>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            itemService.delete(id);
            resp.getWriter().println("<h3>Item deleted!</h3>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String itemName = req.getParameter("item_name");
            double pledgeAmount = Double.parseDouble(req.getParameter("item_pledge_amount"));
            String itemStatus = req.getParameter("item_status");
            int fkClientId = Integer.parseInt(req.getParameter("fk_client_id"));
            int fkCategoryId = Integer.parseInt(req.getParameter("fk_category_id"));

            Items item = new Items(itemName, pledgeAmount, itemStatus, fkClientId, fkCategoryId);

            itemService.add(item);

            resp.getWriter().println("<h3>Item added!</h3>");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            String itemName = req.getParameter("item_name");
            double pledgeAmount = Double.parseDouble(req.getParameter("item_pledge_amount"));
            String itemStatus = req.getParameter("item_status");
            int fkClientId = Integer.parseInt(req.getParameter("fk_client_id"));
            int fkCategoryId = Integer.parseInt(req.getParameter("fk_category_id"));

            Items item = new Items(id, itemName, pledgeAmount, itemStatus, fkClientId, fkCategoryId);

            itemService.update(item);

            resp.getWriter().println("<h3>Item updated!</h3>");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}