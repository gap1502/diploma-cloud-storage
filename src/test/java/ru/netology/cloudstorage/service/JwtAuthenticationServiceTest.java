package ru.netology.cloudstorage.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import ru.netology.cloudstorage.config.JwtTokenUtil;
import ru.netology.cloudstorage.model.JwtRequest;
import ru.netology.cloudstorage.model.JwtResponse;
import ru.netology.cloudstorage.model.UserDao;
import ru.netology.cloudstorage.repository.JwtAuthenticationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class JwtAuthenticationServiceTest {

    public static final String TOKEN_1 = "Token1";
    public static final String USERNAME_1 = "Username1";
    public static final String PASSWORD_1 = "Password1";
    public static final UserDao USER_1 = new UserDao(USERNAME_1, PASSWORD_1, null);
    public static final JwtRequest JWT_REQUEST = new JwtRequest(USERNAME_1, PASSWORD_1);
    public static final JwtResponse JWT_RESPONSE = new JwtResponse(TOKEN_1);
    public static final UsernamePasswordAuthenticationToken USERNAME_PASSWORD_AUTHENTICATION_TOKEN = new UsernamePasswordAuthenticationToken(USERNAME_1, PASSWORD_1);
    public static final String BEARER_TOKEN = "Bearer Token";
    public static final String BEARER_TOKEN_SUBSTRING_7 = BEARER_TOKEN.substring(7);

    @InjectMocks
    private JwtAuthenticationService jwtAuthenticationService;

    @Mock
    private JwtAuthenticationRepository jwtAuthenticationRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private UserService userService;


    @Test
    void createAuthenticationToken() {
        Mockito.when(userService.loadUserByUsername(USERNAME_1)).thenReturn(USER_1);
        Mockito.when(jwtTokenUtil.generateToken(USER_1)).thenReturn(TOKEN_1);
        assertEquals(JWT_RESPONSE, jwtAuthenticationService.createAuthenticationToken(JWT_REQUEST));
        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(USERNAME_PASSWORD_AUTHENTICATION_TOKEN);
        Mockito.verify(jwtAuthenticationRepository, Mockito.times(1)).putTokenAndUsername(TOKEN_1, USERNAME_1);
    }

    @Test
    void logoutAuthenticationTokenAndUsername() {
        Mockito.when(jwtAuthenticationRepository.getUsernameByToken(BEARER_TOKEN_SUBSTRING_7)).thenReturn(USERNAME_1);
        jwtAuthenticationService.logoutAuthenticationTokenAndUsername(BEARER_TOKEN);
        Mockito.verify(jwtAuthenticationRepository, Mockito.times(1)).getUsernameByToken(BEARER_TOKEN_SUBSTRING_7);
        Mockito.verify(jwtAuthenticationRepository, Mockito.times(1)).removeTokenAndUsername(BEARER_TOKEN_SUBSTRING_7);
    }
}
