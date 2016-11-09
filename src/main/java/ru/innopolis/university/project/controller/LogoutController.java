package ru.innopolis.university.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by innopolis on 30.10.16.
 * Controller для выхода из системы
 */
public class LogoutController extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(LogoutController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        logger.debug("session invalidate");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
        logger.debug("user logout");
    }
}
