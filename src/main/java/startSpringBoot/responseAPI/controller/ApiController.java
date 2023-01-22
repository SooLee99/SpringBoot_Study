package startSpringBoot.responseAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import startSpringBoot.responseAPI.dto.User;

@RestController
@RequestMapping("/api")
public class ApiController {

    // Text로 반환하는 방식. (거의 사용 X)
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    // Json을 반환하는 방식
    // request -> object mapper -> object -> method
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user;    // 200 OK
    }

    // RequestEntity
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
