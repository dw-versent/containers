package net.tqxr.containers.app;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "\n**** Greetings from Spring Boot! ****\n\n";
    }

    @RequestMapping("/message")
    public String message() {

        return "{\"message\":\"HELLO THIS IS THE MESSAGE\", \"date\":\"2016\"}";

    }

}
