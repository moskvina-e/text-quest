package com.javarush.quest.controller;

import com.javarush.quest.model.QuestStep;
import com.javarush.quest.service.QuestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Новая версия
 * GameServlet - класс, обрабатывающий логику игры.
 * Использует HttpSession для хранения между запросами.
 * Проверяет ввод пользователя, устанавливает результат в сессию.
 * Использует sendRedirect для предотвращения повторной отправки формы.
 */

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private final QuestService questService = new QuestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stepID = req.getParameter("step");
        if (stepID == null || stepID.isEmpty())
            stepID = "start";

        QuestStep step = questService.getStep(stepID);
        if (step == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Шаг не найден!");
            return;
        }

        req.setAttribute("step", step);
        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Получаем параметры
        String answer = req.getParameter("answer");
        String currentStepID = req.getParameter("currentStep");

        // Валидация
        if (answer == null || currentStepID == null) {
            resp.sendRedirect(req.getContextPath() + "/game?step=start");
            return;
        }

        // Получить текущий шаг
        QuestStep currentStep = questService.getStep(currentStepID);
        if (currentStep == null) {
            resp.sendRedirect(req.getContextPath() + "/game?step=start");
        }

        // Определить следующий
        String nextStepID;
        if ("1".equals(answer)) {
            nextStepID = currentStep.getNextStepId1();
        } else {
            nextStepID = currentStep.getNextStepId2();
        }

        // Обновить статистику
        if (questService.isFinalStep(nextStepID)) {
            Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
            if (gamesPlayed == null)
                gamesPlayed = 0;
            gamesPlayed++;
            session.setAttribute("gamesPlayed", gamesPlayed);

        }

        // Перенаправить на следующий шаг
        resp.sendRedirect(req.getContextPath() + "/game?step=" + nextStepID);

    }

    }
