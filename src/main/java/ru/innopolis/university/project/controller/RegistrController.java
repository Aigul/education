package ru.innopolis.university.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.university.project.entity.Roles;
import ru.innopolis.university.project.entity.User;
import ru.innopolis.university.project.services.UserService;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by innopolis on 28.10.16.
 * Controller для регистрации пользователя
 */
@Controller
public class RegistrController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(RegistrController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage() {
        logger.debug("Get registration.jsp");
        return new ModelAndView("registration");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registrationUser() throws ServletException, IOException {
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setAge(Integer.parseInt(request.getParameter("age")));
        user.setRole(Roles.ROLE_USER);

        logger.debug("User = " + user.toString());

        User userReseult = userService.add(user);
        if(userReseult.getId() == 0L && userReseult.getEmail().isEmpty() && userReseult.getAge() == 0){
            logger.error("Email is dublicate");
            request.setAttribute("error" , "Этот email уже зарегистрирован");
            request.getRequestDispatcher("views/registration.jsp").forward(request, response);
            return null;
        }
        logger.debug("User added");

        return new ModelAndView("login");
    }
}
