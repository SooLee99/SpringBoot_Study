package ilter_and_interceptor.controller;
import ilter_and_interceptor.annotation.AuthUser;
import ilter_and_interceptor.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AuthUser
@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/post")
    public User post(@RequestBody User user){
        return user;
    }

    @DeleteMapping("/delete")
    public ResponseEntity post(){
        return ResponseEntity.ok().build();
    }
}