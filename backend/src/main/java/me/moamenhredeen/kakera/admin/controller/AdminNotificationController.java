package me.moamenhredeen.kakera.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/notifications")
public class AdminNotificationController {

    @GetMapping
    public String notifications(){
        return "admin/notifications";
    }
}
