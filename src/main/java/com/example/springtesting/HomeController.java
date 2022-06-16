package com.example.springtesting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @RequestMapping(path="/greet")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    @GetMapping("/add")
    public Object add(
        @RequestParam(value="a", defaultValue = "0") Float a,
        @RequestParam(value="b", defaultValue = "0") Float b)
    {
        return a+b;
    }

    @GetMapping("/div")
    public Object div(
            @RequestParam(value="a", defaultValue = "0") Float a,
            @RequestParam(value="b", defaultValue = "0") Float b)
    {
        return a/b;
    }

    @GetMapping("/mult")
    public Object mult(
            @RequestParam(value="a", defaultValue = "0") Float a,
            @RequestParam(value="b", defaultValue = "0") Float b)
    {
        return a*b;
    }

    @GetMapping("/sub")
    public Object sub(
            @RequestParam(value="a", defaultValue = "0") Float a,
            @RequestParam(value="b", defaultValue = "0") Float b)
    {
        return a-b;
    }


}
