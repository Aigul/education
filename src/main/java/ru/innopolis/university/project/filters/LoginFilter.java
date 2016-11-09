package ru.innopolis.university.project.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.project.controller.LoginController;
import ru.innopolis.university.project.controller.RegistrController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by innopolis on 30.10.16.
 */
public class LoginFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Filter init");
    }

    /**
     * Фильтр для проверки авторизованности пользователя
     * @param req
     * @param resp
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            logger.debug("User is authorization");
            chain.doFilter(request, response);
        } else if(request.getRequestURI().endsWith("/registration")) {
            logger.debug("User get registration");
            req.getRequestDispatcher("/registration").forward(req, resp);
        } else {
            logger.debug("User get login");
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
        logger.debug("Filter destroy");
    }
}
