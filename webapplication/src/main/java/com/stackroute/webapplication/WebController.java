package com.stackroute.webapplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    String getAppIndex() {
        return "index.html";
    }
}