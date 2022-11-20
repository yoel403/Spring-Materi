package com.example.LatSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting {
    @GetMapping("/hello")

    public String hello(@RequestParam(value = "username", defaultValue = "World") String name){
        return String.format("Hallo %s" , name);
    }

    @GetMapping("/hello2/{name}")
    public String hello2(@PathVariable String name) {
        return String.format("halo lagi %s", name);
    }

    @GetMapping("/hello3")
    public String name(@RequestBody String name) {
        return String.format("Halo yang ke tiga %s", name);
    }

}
