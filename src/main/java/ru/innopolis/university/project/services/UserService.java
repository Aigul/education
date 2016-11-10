package ru.innopolis.university.project.services;

import ru.innopolis.university.project.entity.User;

import java.sql.SQLException;

/**
 * Created by innopolis on 10.11.16.
 */
public interface UserService {

    /**
     * Добавление нового пользователя в БД
     * @param user User
     * @return User
     */
    User add(User user);

    /**
     * Поиск пользователя по email
     * @param email String
     * @return User
     */
    User find(String email);

    /**
     * Изменения данных пользователя
     * @param user User
     */
    void update(User user);
}
