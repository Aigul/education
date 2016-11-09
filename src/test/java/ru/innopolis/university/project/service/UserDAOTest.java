package ru.innopolis.university.project.service;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.project.controller.LoginController;
import ru.innopolis.university.project.model.User;
import ru.innopolis.university.project.services.UserDAO;

import java.sql.SQLException;

/**
 * Created by innopolis on 30.10.16.
 */
public class UserDAOTest {

    private Logger logger = LoggerFactory.getLogger(UserDAOTest.class);

    private Mockery context;

    @Before
    public void before(){
        logger.info("Test start");
        this.context = new JUnit4Mockery();
    }

    @Test
    public void testFind() throws SQLException {
        final UserDAO userDAO = context.mock(UserDAO.class);
        String email = "Hannanova94@mail.com";
        User user = new User();
        user.setId(1);
        user.setAge(22);
        user.setName("Aigul");
        user.setEmail(email);
        user.setPassword("12345");
        context.checking(new Expectations(){{
            oneOf(userDAO).find(email);
            will(returnValue(user));
        }});

        Assert.assertEquals(user, userDAO.find(email));
    }

    @After
    public void after(){
        logger.info("Test finish");
    }
}
