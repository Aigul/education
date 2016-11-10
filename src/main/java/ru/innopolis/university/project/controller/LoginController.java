package ru.innopolis.university.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.university.project.entity.User;
import ru.innopolis.university.project.services.UserService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by innopolis on 30.10.16.
 * Controller для авторизации пользователя
 */
@Controller
public class LoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(){
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView authLogin() throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        logger.debug("Email " + email + " " + " Password " + password);
        if ("".equals(email) || "".equals(password)){
            logger.debug("Input data");
            request.setAttribute("error", "Введите данные");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return null;
        }
        logger.debug("get user by email");
        User user = userService.find(email);

        if (user != null && password.equals(user.getPassword())) {
            logger.debug("User is not null and password is valid");
            request.getSession().setAttribute("user_id", user.getId());
            request.getSession().setAttribute("user", user.getEmail());

            return new ModelAndView("redirect:/home");

        } else {
            logger.error("Login or password invalid");
            request.setAttribute("error", "Неправильный логин или пароль. Попробуйте еще раз.");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return null;
        }
    }
}
