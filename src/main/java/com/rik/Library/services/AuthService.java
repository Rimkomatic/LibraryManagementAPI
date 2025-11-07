package com.rik.Library.services;

import com.rik.Library.dto.Auth.AuthRequest;
import com.rik.Library.dto.Auth.AuthResponse;
import com.rik.Library.entity.Member;
import com.rik.Library.exception.EmailNotFoundException;
import com.rik.Library.exception.PasswordMismatchException;
import com.rik.Library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {

    private final MemberRepository repository;

    AuthService(MemberRepository memberRepository)
    {
        this.repository = memberRepository;
    }

    public AuthResponse handleLogin(AuthRequest user)
    {
        Member member = this.repository.findByEmail(user.email())
                .orElseThrow(() -> new EmailNotFoundException("User not found"));

        if(!Objects.equals(user.password(), member.getPassword())){
            throw new PasswordMismatchException("Password is not Correct!");
        }


        return new AuthResponse(
                member.getEmail(),
                member.getId(),
                member.getName()
                );
    }

}
