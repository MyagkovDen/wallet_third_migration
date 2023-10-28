package com.denismiagkov.walletservice.infrastructure.in.servlets;

import com.denismiagkov.walletservice.application.controller.Controller;
import com.denismiagkov.walletservice.application.dto.PlayerDto;
import com.denismiagkov.walletservice.application.service.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.testcontainers.shaded.org.awaitility.Awaitility.given;

class RegistrationServletTest {

    RegistrationServlet servlet;

    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsb2dpbjEiLCJleHAiOjE2OTgzODgxMDAsInVzZXJJZCI6MX0.J74p4eczxQ-" +
            "wwfaTSl0zWrStYAs-XejjfQuJPYgiP6nXe7SMrKbRRH-Z238nfl0bRpHNBEyl9QTOMQuSNnGdmg";
    String input = "{\n" +
            "    \"name\": \"Ivan\",\n" +
            "    \"surname\": \"Sidorov\",\n" +
            "    \"email\": \"petrov@mail.ru\",\n" +
            "    \"login\": \"user\",\n" +
            "    \"password\": \"123456\"\n" +
            "}";
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ServletInputStream inputStream;

//    Controller controller;
//
//    ObjectMapper objectMapper;
//    StringWriter stringWriter;
//    PrintWriter printWriter;

    @BeforeEach
    void setUp() throws IOException, ServletException {
        MockitoAnnotations.initMocks(this);
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
//        when(request.getInputStream()).thenReturn(inputStream);
//        when(inputStream.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
//            byte[] buffer = invocation.getArgument(0);
//            int offset = invocation.getArgument(1);
//            int length = invocation.getArgument(2);
//            return byteArrayInputStream.read(buffer, offset, length);
//        });
        ;

        servlet = new RegistrationServlet();
        servlet.init();

//         stringWriter = new StringWriter();
//         printWriter = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(printWriter);

    }

    @Test
    public void testDoPost_Correct_Input() throws ServletException, IOException {
        // Arrange
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        ServletInputStream inputStream = mock(ServletInputStream.class);
//        PlayerDto playerDto = objectMapper.readValue(request.getInputStream(), PlayerDto.class);
//        String jsonRequest = "{\n" +
//                "    \"name\": \"Ivan1\",\n" +
//                "    \"surname\": \"Petrov\",\n" +
//                "    \"email\": \"petrov@mail.ru\",\n" +
//                "    \"login\": \"user\",\n" +
//                "    \"password\": \"123\"\n" +
//                "}";
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(jsonRequest.getBytes(StandardCharsets.UTF_8));
//        when(request.getInputStream()).thenReturn(inputStream);
//        when(inputStream.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
//            byte[] buffer = invocation.getArgument(0);
//            int offset = invocation.getArgument(1);
//            int length = invocation.getArgument(2);
//            return byteArrayInputStream.read(buffer, offset, length);
//        });



//        ObjectMapper objectMapper = new ObjectMapper();
//        PlayerDto playerDto = objectMapper.readValue(request.getInputStream(), PlayerDto.class);
//        System.out.println(playerDto);
//
//
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(printWriter);
//        Controller controller = mock(Controller.class);
//        doNothing().when(controller).registerPlayer(playerDto);
//
//        System.out.println(playerDto.getSurname() + " = " + playerDto.getSurname().length());
//        DataValidator.checkRegistrationForm(playerDto);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        when(request.getInputStream()).thenReturn(inputStream);
        when(inputStream.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
            byte[] buffer = invocation.getArgument(0);
            int offset = invocation.getArgument(1);
            int length = invocation.getArgument(2);
            return byteArrayInputStream.read(buffer, offset, length);
        });
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
//
//        // Act
       servlet.doPost(request, response);
//
//        // Assert
       // verify(response).setContentType("application/json");
        verify(response).setStatus(HttpServletResponse.SC_CREATED);
        //  assertEquals("{\"message\": \"Игрок Иван Петров зарегистрирован\"}", stringWriter.toString());
    }













    // Arrange
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        ServletInputStream inputStream = mock(ServletInputStream.class);
//
//        String jsonRequest = input;
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(jsonRequest.getBytes(StandardCharsets.UTF_8));
//        when(request.getInputStream()).thenReturn(inputStream);
//        when(inputStream.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
//            byte[] buffer = invocation.getArgument(0);
//            int offset = invocation.getArgument(1);
//            int length = invocation.getArgument(2);
//            return byteArrayInputStream.read(buffer, offset, length);
//        });;
//
//        PlayerDto playerDto = objectMapper.readValue(request.getInputStream(), PlayerDto.class);
//        System.out.println(playerDto);

    // assertEquals(HttpServletResponse.SC_CREATED, response.getStatus());


//
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(printWriter);
//        Controller controller = mock(Controller.class);
//        doNothing().when(controller).registerPlayer(playerDto);
//        DataValidator.checkRegistrationForm(playerDto);

    // Act
    //  new RegistrationServlet(controller).doPost(request, response);

    // Assert
    // verify(response).setContentType("application/json");
    //  verify(response).setStatus(HttpServletResponse.SC_CREATED);
    // assertEquals("{\"message\": \"Игрок John Doe зарегистрирован\"}", stringWriter.toString());
}



