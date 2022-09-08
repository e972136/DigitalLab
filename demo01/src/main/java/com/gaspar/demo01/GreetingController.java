package com.gaspar.demo01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    static AtomicInteger counter = new AtomicInteger();
    static final String template = "Que peps %s";

    @GetMapping
    public Greeting getGreeting(@RequestParam(value="name", defaultValue = "mundo!") String name){
        System.out.println(name);
        return Greeting.of(counter.incrementAndGet(),String.format(template,name));
    }
}
