package ru.netology.cloudstorage.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.netology.cloudstorage.config.JwtTokenUtil;
import ru.netology.cloudstorage.model.JwtRequest;
import ru.netology.cloudstorage.model.JwtResponse;
import ru.netology.cloudstorage.repository.JwtAuthenticationRepository;

@Service
@AllArgsConstructor
public class JwtAuthenticationService {

    private JwtAuthenticationRepository jwtAuthenticationRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        final String username = jwtRequest.getLogin();
        final String password = jwtRequest.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetails = userService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        jwtAuthenticationRepository.putTokenAndUsername(token, username);
        return new JwtResponse(token);
    }

    public void logoutAuthenticationTokenAndUsername(String authtoken) {
        final String token = authtoken.substring(7);
        final String username = jwtAuthenticationRepository.getUsernameByToken(token);
        System.out.println(username + " logout");
        jwtAuthenticationRepository.removeTokenAndUsername(token);
    }
}
