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
import validation.dto.User1;

// [Validation : 유효성 검사]
// - 스프링은 객체의 유효성검사에 사용할 수 있는 Validator 인터페이스를 제공한다.
// - Validator 인터페이스는 유효성검사를 하면서 밸리데이터가 Errors 객체에 유효성검사의 실패내역을 보고할 수 있도록 Errors 객체를 사용해서 동작한다.
// => 특히 Java 에서는 null 값에 대해서 접근할 때, null pointer Exception 이 발생함으로,
//    이러한 부분을 미리 방지하고자 하는 과정이 Validation 이라고 한다.

@RestController
@RequestMapping("/api")
@Validated
// @Validated : AOP 기반으로 메소드의 요청을 가로채서 유효성을 검증하는 방식이며,
//              JSR 표준 기술이 아니라 Spring 프레임워크에서 제공하는 어노테이션 및 기능이다.

// [@Validated 동작 원리]
//  - 인터셉터(MethodValidationInterceptor)가 유효성 검증을 진행하며, 스프링 빈이라면 유효성 검증을 진행할 수 있다.
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
    public ResponseEntity user(@Valid @RequestBody User1 user, BindingResult bindingResult){

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