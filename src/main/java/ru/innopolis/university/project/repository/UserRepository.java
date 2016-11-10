package ru.innopolis.university.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.university.project.entity.User;

/**
 * Created by innopolis on 10.11.16.
 * Репозиторий для работы с таблицей users
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Метод поиска пользователя по email
     * @param email String
     * @return User
     */
    User findByEmail(String email);
}
