package com.geekerstar.webflux.controller;

import com.geekerstar.webflux.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class HelloWebFluxController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, WebFlux !";
    }

    @GetMapping("/user")
    public Mono<User> getUser() {
        User user = new User();
        user.setName("Geekerstar");
        user.setDesc("极客文库，猿码素材");
        return Mono.just(user);
    }


}
