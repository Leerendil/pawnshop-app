package by.vsu.controllers;

import by.vsu.repository.ClientsRepository;
import by.vsu.service.ClientService;
import by.vsu.tableClasses.Clients;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ClientServlet extends HttpServlet {
    private ClientService clientService;

    @Override
    public void init() {
        clientService = new ClientService(new ClientsRepository());
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

    private void getAll(HttpServletResponse resp) {
        try {
            var clients = clientService.getAll();
            resp.getWriter().println("<h2>Clients list:</h2>");
            for (var client : clients) {
                resp.getWriter().println(client.toString() + "<br>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Clients client = clientService.getById(id);
            if (client != null) {
                resp.getWriter().println("<h2>Client:</h2>");
                resp.getWriter().println("<p>Id:" + client.getClient_id() + ", " + client.getFirst_name() + ", " + client.getLast_name() + "</p>");
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
            clientService.delete(id);
            resp.getWriter().println("<h3>Client deleted!</h3>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
