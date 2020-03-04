package com.vv.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/message")
    public String getMessage() {
        return "Hello !!!";
    }

    @GetMapping(value = "/book/{bookName}")
    public Book getBook(@PathVariable String bookName) {
        return new Book(bookName);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/load")
    public String loadData() throws LoadException {
        throw new LoadException("Failed to load!!!!");
    }

}
