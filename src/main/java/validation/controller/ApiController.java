package validation.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import validation.dto.Req;
import validation.dto.User;

@RestController
@RequestMapping("/api")
@Validated
public class ApiController {

    @GetMapping("")
    public String get(
    /*        @Size(min = 1 , max = 10)
            @NotEmpty
            @RequestParam String name,
            @Min(10)
            @RequestParam int age
    */
            @Valid Req req
    ){
        return req.getName() + req.getAge();
    }

    //  (1) 유저정보 검증 => 에러를 확인하는 메소드
    // BindingResult : 검증 오류가 발생할 경우 오류 내용을 보관하는 스프링 프레임워크에서 제공하는 객체입니다.
    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult){

        // bindingResult.hasErrors() : 검증 오류 발생 시, 오류 내용을 조회하는 메소드
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();

            // 해당 BindingResult 객체에서 발생한 에러를 모두 가져와 forEach 를 통해 작업한다
            bindingResult.getAllErrors().forEach(objectError -> {

                // FieldError
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field : "+field.getField());
                System.out.println(message);

                sb.append("field : "+field.getField());
                sb.append("message : "+message);

            });

            // 응답 값 출력 : 에러 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        return ResponseEntity.ok(user);
    }

}