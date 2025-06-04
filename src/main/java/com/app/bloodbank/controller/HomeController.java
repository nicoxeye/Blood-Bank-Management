package com.app.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/admin")
    public String admin() {
        return "admin/index";
    }

    @GetMapping("/hospital")
    public String hospital() {
        return "hospital/index";
    }

    @GetMapping("/bloodbank")
    public String bloodbank() {
        return "bloodbank/index";
    }
}
