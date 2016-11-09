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
 * Controller для личной страницы пользователя и для изменения данных о пользователе
 */
public class UserController extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = userDAO.find((String) req.getSession().getAttribute("user"));
            logger.debug("Get user = " + user);
            req.setAttribute("name", user.getName());
            req.setAttribute("email", user.getEmail());
            req.setAttribute("password", user.getPassword());
            req.setAttribute("age", user.getAge());
            logger.debug("get home.jsp");
            req.getRequestDispatcher("home.jsp").forward(req,resp);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if ("".equals(email) || "".equals(password)){
            logger.error("Email or password is empty");
            req.getSession().setAttribute("error", "Заполните пустые поля.");
            resp.sendRedirect("/home");
            return;
        }

        User user = new User();
        user.setId((Integer) req.getSession().getAttribute("user_id"));
        user.setEmail(email);
        user.setPassword(password);
        user.setName(req.getParameter("name"));
        user.setAge(Integer.parseInt(req.getParameter("age")));

        logger.debug("User = " + user);
        try {
            userDAO.update(user);
            logger.debug("User updated");
            req.getSession().setAttribute("message", "Данные обновлены");
            resp.sendRedirect("/home");
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())){
                logger.error("Dublicate email = " + user.getEmail());
                req.setAttribute("error" , "Этот email уже зарегистрирован");
                req.getRequestDispatcher("home.jsp").forward(req,resp);
                return;
            }
        }
    }
}
