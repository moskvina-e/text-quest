package com.javarush.quest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StartServletTest {

    private StartServlet servlet;

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    HttpSession session;

    @BeforeEach
    void setUp() {
        servlet = new StartServlet();
    }

    @Test
    @DisplayName("Test doGet from StartServlet")
    void testDoGetFromStartServlet() throws ServletException, IOException {
        when(req.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);
        servlet.doGet(req, resp);
        verify(req, times(1)).getRequestDispatcher("/index.jsp");
        verify(dispatcher, times(1)).forward(req, resp);

    }

    @Test
    @DisplayName("Test call getParameter() for playerName")
    void testCallGetParameterPlayerName() throws ServletException, IOException {
        when(req.getParameter("playerName")).thenReturn("Name");
        when(req.getSession()).thenReturn(session);
        servlet.doPost(req, resp);
        verify(req, times(1)).getParameter("playerName");
    }

    @ParameterizedTest
    @ValueSource(strings = {"null", "   "})
    @DisplayName("Test call getParameter() for empty playerName")
    void testCallGetParameterForEmptyPlayerName(String name) throws ServletException, IOException {
        if (name.equals("null"))
            name = null;
        when(req.getParameter("playerName")).thenReturn(name);
        when(req.getSession()).thenReturn(session);
        servlet.doPost(req, resp);
        verify(session).setAttribute("playerName", "Искатель приключений");
    }

    @Test
    @DisplayName("Test save session attributes")
    void testSaveSessionAttributes() throws ServletException, IOException {
        when(req.getParameter("playerName")).thenReturn("Елена");
        when(req.getSession()).thenReturn(session);
        servlet.doPost(req, resp);
        verify(session).setAttribute("playerName", "Елена");
        verify(session).setAttribute("gamesPlayed", 0);
    }

    @Test
    @DisplayName("Test call getSession()")
    void testCallGetSession() throws ServletException, IOException {
        when(req.getSession()).thenReturn(session);
        servlet.doPost(req, resp);
        verify(req, times(1)).getSession();
    }

    @Test
    @DisplayName("Test sendRedirect()")
    void testSendRedirect() throws ServletException, IOException {
        when(req.getContextPath()).thenReturn("/HelloQuest");
        when(req.getSession()).thenReturn(session);
        servlet.doPost(req, resp);
        verify(resp, only()).sendRedirect("/HelloQuest/game?step=start");
    }
}