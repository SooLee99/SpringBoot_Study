package spring_aop.controller;

import org.springframework.web.bind.annotation.*;
import spring_aop.dto.User;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping("/get/{id}")
    public void get(@PathVariable String id, @RequestParam String name){
        System.out.println("get Method : "+id);
        System.out.println("get Method : "+name);
    }

    @PostMapping("/post")
    public void post(@RequestParam User user){
        System.out.println("post Method : "+user);
    }
}
