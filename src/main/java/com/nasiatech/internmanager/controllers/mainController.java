package com.nasiatech.internmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @GetMapping("/index")
    public String showHomePage(){
        System.out.println("Main Controller");
        return "index";
    }


}
