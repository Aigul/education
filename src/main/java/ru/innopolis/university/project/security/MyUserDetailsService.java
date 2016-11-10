package ru.innopolis.university.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.innopolis.university.project.entity.User;
import ru.innopolis.university.project.services.UserService;

/**
 * Created by innopolis on 10.11.16.
 * Класс для authentication-provider
 * Описан в securityContext.xml
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * Метод поиска UserDetails по email
     * @param username String
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.find(username);
        if (user == null) throw new UsernameNotFoundException("User with name " + username + " not found");
        return new UserInfo(user);
    }
}
