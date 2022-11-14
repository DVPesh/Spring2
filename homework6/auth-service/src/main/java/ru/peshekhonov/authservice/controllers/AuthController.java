package ru.peshekhonov.authservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.peshekhonov.api.dto.JwtRequest;
import ru.peshekhonov.api.dto.JwtResponse;
import ru.peshekhonov.api.exceptions.AppError;
import ru.peshekhonov.authservice.services.VisitorService;
import ru.peshekhonov.authservice.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final VisitorService visitorService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                    authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError("CHECK_TOKEN_ERROR", "Некорректный логин или пароль"),
                    HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = visitorService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
