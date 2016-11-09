package ru.innopolis.university.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.project.model.User;
import ru.innopolis.university.project.services.UserDAO;
import ru.innopolis.university.project.services.impl.UserDAOImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by innopolis on 30.10.16.
 * Controller для авторизации пользователя
 */
public class LoginController extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("get login.jsp");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        logger.debug("Email " + email + " " + " Password " + password);
        if ("".equals(email) || "".equals(password)){
            logger.debug("Input data");
            req.setAttribute("error", "Введите данные");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        User user = null;
        try {
            logger.debug("get user by email");
            user = userDAO.find(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user != null && password.equals(user.getPassword())) {
            logger.debug("User is not null and password is valid");
                req.getSession().setAttribute("user_id", user.getId());
                req.getSession().setAttribute("user", user.getEmail());
                resp.sendRedirect("/home");

        } else {
            logger.error("Login or password invalid");
            req.setAttribute("error", "Неправильный логин или пароль. Попробуйте еще раз.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
