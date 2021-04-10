package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo")
public class demoController {

    @GetMapping("/helloWorld")
    public String helloWord(){
        return "hellow word!";
    }
}
