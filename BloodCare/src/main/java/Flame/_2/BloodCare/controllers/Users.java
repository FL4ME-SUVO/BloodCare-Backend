package Flame._2.BloodCare.controllers;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Flame._2.BloodCare.model.UserModel;

@RestController
public class Users {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("hello")
    public UserModel user(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new UserModel(counter.incrementAndGet(), String.format("Hello, %s!", name));
    }
}

