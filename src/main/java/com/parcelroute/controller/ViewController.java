package com.parcelroute.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/parcels")
    public String viewPage(Model model) {
        return "parcels";
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        return "users";
    }

    @GetMapping("/lockers")
    public String viewLockers(Model model) {
        return "lockers";
    }

    @GetMapping("/lockercells")
    public String viewCells(Model model) {
        return "lockercell";
    }
}
