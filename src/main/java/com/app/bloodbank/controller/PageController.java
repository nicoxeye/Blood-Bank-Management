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

    @GetMapping("/admin/bloodbanks")
    public String manageBloodbanksPage2() {
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

    @GetMapping("/bloodbank/hospitals")
    public String manageHospitalsPage() {
        return "bloodbank/hospitals";
    }

    @GetMapping("/hospital/hospitals")
    public String manageHospitalsPage2() {
        return "bloodbank/hospitals";
    }

    @GetMapping("/admin/hospitals")
    public String manageHospitalsPage3() {
        return "bloodbank/hospitals";
    }

    @GetMapping("/bloodbank/statistics")
    public String manageStatisticsPage1() { return "bloodbank/statistics"; }

    @GetMapping("/bloodbank/donors/add")
    public String manageDonorAddPage() { return "bloodbank/donor/create"; }

    @GetMapping("/bloodbank/donations/register-now")
    public String manageRegisterDonationPage() { return "bloodbank/donation/createnow"; }

    @GetMapping("/bloodbank/donations/register-past")
    public String manageRegisterDonationPage2() { return "bloodbank/donation/createpast"; }

}
