package ru.innopolis.university.project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.university.project.entity.User;
import ru.innopolis.university.project.repository.UserRepository;
import ru.innopolis.university.project.services.UserService;

/**
 * Created by innopolis on 10.11.16.
 * Реализация интерфейса UserService для работы с контроллером
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Добавление нового пользователя в БД
     * @param user User
     * @return User
     */
    @Override
    public User add(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        } else {
            return new User();
        }
    }

    /**
     * Поиск пользователя по email
     * @param email String
     * @return User
     */
    @Override
    public User find(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Изменения данных пользователя
     * @param user User
     */
    @Override
    public void update(User user) {

    }
}
