package com.example.springtesting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    private static GreetingService service;

    public GreetingController(GreetingService service) {
        GreetingController.service = service;
    }

    @RequestMapping(path="/")
    public @ResponseBody String greeting() {
        return service.greet();
    }
}
