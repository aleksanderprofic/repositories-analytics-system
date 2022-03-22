package pl.edu.uj.ii.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @GetMapping("/halko")
    public String hello() {
        return "Hello world";
    }
}
