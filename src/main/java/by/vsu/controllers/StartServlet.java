package by.vsu.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");

        String html = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Welcome</title>
        <style>
            * { margin: 0; padding: 0; box-sizing: border-box; }
            body { height: 100vh; display: flex; justify-content: center; align-items: center; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); font-family: Arial, sans-serif; }
            h1 { font-size: 5rem; color: white; text-shadow: 2px 2px 10px rgba(0,0,0,0.3); letter-spacing: 5px; animation: fadeIn 1.5s ease-in; }
            @keyframes fadeIn { from { opacity: 0; transform: translateY(-20px); } to { opacity: 1; transform: translateY(0); } }
        </style>
    </head>
    <body>
        <div><h1>WELCOME</h1></div>
    </body>
    </html>
    """;

        resp.getWriter().println(html);
    }
}
