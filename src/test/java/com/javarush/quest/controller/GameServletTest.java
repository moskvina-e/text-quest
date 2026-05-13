package com.javarush.quest.controller;

import com.javarush.quest.model.QuestStep;
import com.javarush.quest.service.QuestService;
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
class GameServletTest {

    private GameServlet servlet;

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    HttpSession session;
    @Mock
    QuestService service;
    @Mock
    QuestStep questStep;

    @BeforeEach
    void setUp() {
        servlet = new GameServlet();
    }

    @Test
    @DisplayName("Test doGet from GameServlet")
    void testDoGetFromGameServlet() throws ServletException, IOException {
        when(req.getRequestDispatcher("/game.jsp")).thenReturn(dispatcher);
        servlet.doGet(req, resp);
        verify(req, times(1)).getRequestDispatcher("/game.jsp");
        verify(dispatcher, times(1)).forward(req, resp);

    }

    @Test
    @DisplayName("Test doPost call getParameter() for null")
    void testDoPostCallGetParameterForNull() throws ServletException, IOException {
        when(req.getParameter("answer")).thenReturn(null);
        when(req.getParameter("currentStep")).thenReturn(null);
        when(req.getSession()).thenReturn(session);
        when(req.getContextPath()).thenReturn("/HelloQuest");
        servlet.doPost(req, resp);
        verify(resp).sendRedirect("/HelloQuest/game?step=start");
        verify(session, never()).setAttribute(eq("gamesPlayed"), anyInt());
    }


}