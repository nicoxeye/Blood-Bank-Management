package com.app.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/admin/addresses")
    public String manageAddressesPage() {
        return "admin/addresses/index";
    }

    @GetMapping("/bloodbank/bloodbanks")
    public String manageBloodbanksPage() {
        return "bloodbank/bloodbanks";
    }

    @GetMapping("/bloodbank/donors")
    public String manageDonorsPage() {
        return "bloodbank/donor/donors";
    }

    @GetMapping("/bloodbank/donations")
    public String manageDonationsPage() {
        return "bloodbank/donation/index";
    }
}
