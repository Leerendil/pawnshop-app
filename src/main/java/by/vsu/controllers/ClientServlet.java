package by.vsu.controllers;

import by.vsu.repository.ClientsRepository;
import by.vsu.service.ClientService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class ClientServlet extends HttpServlet {
    private ClientService clientService;

    @Override
    public void init() {
        clientService = new ClientService(new ClientsRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html; charset=utf-8");
        try {
            var clients = clientService.getAll();
            resp.getWriter().println("<h2>Clients list:</h2>");
            for (var client : clients) {
                resp.getWriter().println("<p>"+ client.getLast_name() + "</p>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
