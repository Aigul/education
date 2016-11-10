package ru.innopolis.university.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by innopolis on 30.10.16.
 * Controller для выхода из системы
 */
@Controller
public class LogoutController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(LogoutController.class);

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView getLogoutPage() {
        request.getSession().invalidate();
        logger.debug("session invalidate");
        logger.debug("user logout");
        return new ModelAndView("login");
    }
}
