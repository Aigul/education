package ru.innopolis.university.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.project.model.User;
import ru.innopolis.university.project.services.UserDAO;
import ru.innopolis.university.project.services.impl.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by innopolis on 28.10.16.
 * Controller для регистрации пользователя
 */
public class RegistrController extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(RegistrController.class);

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Get registration.jsp");
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        User user = new User();
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setName(req.getParameter("name"));
        user.setAge(Integer.parseInt(req.getParameter("age")));

        logger.debug("User = " + user.toString());

        try {
            userDAO.add(user);
            logger.debug("User added");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())){
                logger.error("Email is dublicate");
                req.setAttribute("error" , "Этот email уже зарегистрирован");
                req.getRequestDispatcher("registration.jsp").forward(req,resp);
                return;
            }
        }

        resp.sendRedirect("/login");
    }
}
