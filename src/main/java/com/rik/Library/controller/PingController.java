package com.rik.Library.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("http://localhost:5173/")
@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
