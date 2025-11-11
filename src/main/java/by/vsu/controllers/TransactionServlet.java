package by.vsu.controllers;

import by.vsu.repository.TransactionRepository;
import by.vsu.service.TransactionService;
import by.vsu.tableClasses.Transactions;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TransactionServlet extends HttpServlet {
    private TransactionService transactionService;

    @Override
    public void init() {
        transactionService = new TransactionService(new TransactionRepository());
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
            var transactions = transactionService.getAll();
            resp.getWriter().println("<h2>Transactions list:</h2>");
            for (var transaction : transactions) {
                resp.getWriter().println(transaction.toString().replace("\n", "<br>") + "<br><br>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Transactions transaction = transactionService.getById(id);
            if (transaction != null) {
                resp.getWriter().println("<h2>Transaction:</h2>");
                resp.getWriter().println(transaction.toString().replace("\n", "<br>"));
            } else {
                resp.getWriter().println("<h3>No transaction with such id!</h3>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            transactionService.delete(id);
            resp.getWriter().println("<h3>Transaction deleted!</h3>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String transactionType = req.getParameter("transaction_type");
            double amount = Double.parseDouble(req.getParameter("amount"));
            int transactionDate = Integer.parseInt(req.getParameter("transaction_date"));
            int fkEmployeeId = Integer.parseInt(req.getParameter("fk_employee_id"));
            int fkItemId = Integer.parseInt(req.getParameter("fk_item_id"));

            Transactions transaction = new Transactions(transactionType, amount, transactionDate, fkEmployeeId, fkItemId);

            transactionService.add(transaction);

            resp.getWriter().println("<h3>Transaction added!</h3>");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            String transactionType = req.getParameter("transaction_type");
            double amount = Double.parseDouble(req.getParameter("amount"));
            int transactionDate = Integer.parseInt(req.getParameter("transaction_date"));
            int fkEmployeeId = Integer.parseInt(req.getParameter("fk_employee_id"));
            int fkItemId = Integer.parseInt(req.getParameter("fk_item_id"));

            Transactions transaction = new Transactions(id, transactionType, amount, transactionDate, fkEmployeeId, fkItemId);

            transactionService.update(transaction);

            resp.getWriter().println("<h3>Transaction updated!</h3>");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}