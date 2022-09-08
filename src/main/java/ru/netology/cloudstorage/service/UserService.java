package ru.netology.cloudstorage.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netology.cloudstorage.exception.UnauthorizedException;
import ru.netology.cloudstorage.model.UserDao;
import ru.netology.cloudstorage.repository.UserRepository;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDao user = userDao.findByUsername(username);
        if (user == null) {
            System.out.println("User Unauthorized");
            throw new UnauthorizedException("User Unauthorized");
        }
        return user;
    }
}
