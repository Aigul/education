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

import java.sql.SQLException;

/**
 * Created by innopolis on 30.10.16.
 * Controller для личной страницы пользователя и для изменения данных о пользователе
 */
@Controller
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private String redirect = "redirect:/home";

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        User user = userService.find((String) request.getSession().getAttribute("user"));
        logger.debug("Get user = " + user);
        request.setAttribute("name", user.getName());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("password", user.getPassword());
        request.setAttribute("age", user.getAge());
        logger.debug("get home.jsp");

        return new ModelAndView("home");
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView updateUser() {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if ("".equals(email) || "".equals(password)){
            logger.error("Email or password is empty");
            request.getSession().setAttribute("error", "Заполните пустые поля.");
            return new ModelAndView(redirect);
        }

        User user = new User();
        user.setId((Integer) request.getSession().getAttribute("user_id"));
        user.setEmail(email);
        user.setPassword(password);
        user.setName(request.getParameter("name"));
        user.setAge(Integer.parseInt(request.getParameter("age")));

        logger.debug("User = " + user);
        userService.update(user);
        logger.debug("User updated");
        request.getSession().setAttribute("message", "Данные обновлены");

        return new ModelAndView(redirect);
    }
}
