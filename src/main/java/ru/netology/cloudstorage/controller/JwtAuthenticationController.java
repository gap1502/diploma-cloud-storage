package ru.netology.cloudstorage.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.cloudstorage.model.JwtRequest;
import ru.netology.cloudstorage.model.JwtResponse;
import ru.netology.cloudstorage.service.JwtAuthenticationService;


@RestController
@AllArgsConstructor
public class JwtAuthenticationController {

    private JwtAuthenticationService jwtAuthenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JwtResponse login(@RequestBody JwtRequest jwtRequest) {
        return jwtAuthenticationService.createAuthenticationToken(jwtRequest);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authtoken) {
        jwtAuthenticationService.logoutAuthenticationTokenAndUsername(authtoken);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
