package com.gmail.fediushyn.anton.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexContorller {

    @GetMapping
    public String index(){
        return "index";
    }
}
