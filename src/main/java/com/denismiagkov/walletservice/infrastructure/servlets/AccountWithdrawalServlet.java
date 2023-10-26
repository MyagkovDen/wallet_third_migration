package com.denismiagkov.walletservice.infrastructure.servlets;

import com.denismiagkov.walletservice.application.controller.Controller;
import com.denismiagkov.walletservice.application.service.Service;
import com.denismiagkov.walletservice.infrastructure.DatabaseConnection;
import com.denismiagkov.walletservice.login_service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/players/withdrawal")
public class AccountWithdrawalServlet extends HttpServlet {
    Controller controller;
    ObjectMapper objectMapper;
    AuthService authService;

    @Override
    public void init() throws ServletException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            Connection connection = dbConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        controller = new Controller(new Service());
        objectMapper = new ObjectMapper();
        authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String token = authService.getTokenFromRequest(req);
        try {
            if (authService.validateAccessToken(token)) {
                String login = authService.getLoginFromToken(token);
                JsonNode jsonNode = objectMapper.readTree(req.getInputStream());
                String input = jsonNode.get("amount").asText();
                if (DataValidator.checkNumber(input)) {
                    BigDecimal amount = jsonNode.get("amount").decimalValue();
                    controller.writeOffFunds(login, amount);
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write("{\"message\": \"С вашего счета списана сумма " + amount + " " +
                            "денежных единиц\"");
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("{\"message\": \"Введено некорректное число!\"");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().write("{\"message\": \"Не пройдена аутентификация!\"");
            }
        } catch (JsonProcessingException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Ошибка форматирования JSON");
        }
    }
}
