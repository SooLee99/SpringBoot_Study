package start_SpringBoot.responseAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start_SpringBoot.responseAPI.dto.User;

@RestController
@RequestMapping("/api")
public class ApiController {
    // Response API : 브라우저로 보낼 출력 스트림을 전송.

    // <Text로 반환하는 방식.> (거의 사용 X)
    // "GetMapping"으로 넘어온 특정 파라미터 리턴하는 메서드.
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    // <"Json"을 입력받는 방식>
    // (1) 브라우저에서 "Json"을 "request"를 통해 입력받음.
    // (2) object mapper(도구)를 통해 object(결과물)로 변환.
    // (3) 변환된 "object"를 "method"에 반환시킴.

    // <"Json"으로 출력하는 방식>
    // (1) object(입력)를 object mapper(도구)를 통해서 "Json"으로 변환.
    // (2) "response"를 통해서 브라우저로 출력.
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user;    // 200 OK
    }

    // RequestEntity 란?
    //  - "HttpEntity"를 상속 받는, 결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스이다.

    // HttpStatus.CREATED : 결과 값이 201일 경우. => User 객체에 정보를 넣을 수 있음.
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
