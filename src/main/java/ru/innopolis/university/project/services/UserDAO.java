package ru.innopolis.university.project.services;

import ru.innopolis.university.project.model.User;

import java.sql.SQLException;

/**
 * Created by innopolis on 28.10.16.
 * Интерфейс для работы с БД
 */
public interface UserDAO {

    /**
     * Метод для добавления нового пользователя
     * @param user User
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException
     */
    void add(User user)  throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException;

    /**
     * Метод для поиска пользователя по email
     * @param email String
     * @return User
     * @throws SQLException
     */
    User find(String email) throws SQLException;

    /**
     * Метод для изменения данных пользователя
     * @param user User
     * @throws SQLException
     */
    void update(User user) throws SQLException;

}
