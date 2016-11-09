package ru.innopolis.university.project.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.project.controller.RegistrController;
import ru.innopolis.university.project.model.User;
import ru.innopolis.university.project.services.UserDAO;

import java.sql.*;

/**
 * Created by innopolis on 29.10.16.
 * Имплементация интерфейса UserDAO
 */
public class UserDAOImpl implements UserDAO {

    private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);


    private static Connection con;

    /*
      Блок инициализации для соединения с БД
     */
    static {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/project_education","postgres","postgres");
            logger.debug("Connection is true");
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Метод для добавления нового пользователя
     * @param user User
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException
     */
    @Override
    public void add(User user) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        PreparedStatement ps=con.prepareStatement("INSERT INTO \"user\" (email, password, name, age) VALUES (?,?,?,?)");

        ps.setString(1,user.getEmail());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getName());
        ps.setInt(4, user.getAge());
        ps.executeUpdate();
        logger.debug("Add user = " + user);
    }

    /**
     * Метод для поиска пользователя по email
     * @param email String
     * @return User
     * @throws SQLException
     */
    @Override
    public User find(String email) throws SQLException {
        logger.debug("Find user by email = " + email);

        User user = new User();
        PreparedStatement ps=con.prepareStatement("SELECT * FROM \"user\" WHERE email = ?");

        ps.setString(1,email);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAge(rs.getInt("age"));
        }
        logger.debug("User = " + user);
        return user;
    }

    /**
     * Метод для изменения данных пользователя
     * @param user User
     * @throws SQLException
     */
    @Override
    public void update(User user) throws SQLException {
        logger.debug("Update byUserId = " + user.getId());
        PreparedStatement ps = con.prepareStatement("UPDATE \"user\" SET email = ?, name = ?, password = ?, age = ? WHERE id = ?");
        ps.setString(1,user.getEmail());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());
        ps.setInt(4,user.getAge());
        ps.setInt(5,user.getId());

        ps.executeUpdate();

        logger.debug("user updated = " + user);
    }
}
