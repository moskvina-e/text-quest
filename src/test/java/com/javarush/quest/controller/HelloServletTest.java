package com.javarush.quest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelloServletTest {

    private HelloServlet servlet;

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        servlet = new HelloServlet();
    }

    @Test
    @DisplayName("Test doGet from HelloServlet")
    void testDoGetFromHelloServlet() throws ServletException, IOException {
        when(req.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);
        servlet.doGet(req, resp);
        verify(req, times(1)).getRequestDispatcher("/index.jsp");
        verify(dispatcher, times(1)).forward(req, resp);

    }

}