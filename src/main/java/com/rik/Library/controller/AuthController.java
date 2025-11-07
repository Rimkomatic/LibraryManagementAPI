package com.rik.Library.controller;


import com.rik.Library.dto.Auth.AuthRequest;
import com.rik.Library.dto.Auth.AuthResponse;
import com.rik.Library.services.AuthService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public AuthResponse checkLogin(@RequestBody AuthRequest user)
    {
        return this.authService.handleLogin(user);
    }

}
