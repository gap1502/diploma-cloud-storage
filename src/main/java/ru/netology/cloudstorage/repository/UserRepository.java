package ru.netology.cloudstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.cloudstorage.model.UserDao;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserDao, String> {
    UserDao findByUsername(String username);

}
