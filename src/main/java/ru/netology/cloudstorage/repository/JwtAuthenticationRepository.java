package ru.netology.cloudstorage.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class JwtAuthenticationRepository {

    private final Map<String, String> dataTokensAndUsernames = new ConcurrentHashMap<>();

    public void putTokenAndUsername(String token, String username) {
        dataTokensAndUsernames.put(token, username);
    }
}
