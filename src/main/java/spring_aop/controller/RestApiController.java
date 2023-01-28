package spring_aop.controller;

import org.springframework.web.bind.annotation.*;
import spring_aop.annotation.Timer;
import spring_aop.dto.User;

// AOP : Aspect Oriented Programming -> (관점 지향 프로그래밍)이라고 불린다.
//  - 관점 지향 : 쉽게 말해 어떤 로직을 기준으로 핵심적인 관점, 부가적인 관점으로 나누어서 보고 그 관점을 기준으로 각각 모듈화하겠다는 것이다.

//  - 스프링 어플리케이션은 대부분 특별한 경우를 제외하고,
//    MVC 웹 어플리케이션에서는 WebLayer, BusinessLayer, DataLayer 로 정의한다.
//  (1) Web Layer : REST API 를 제공하며, Client 중심의 로직 적용.
//  (2) Business Layer : 내부 정책에 따른 로직을 개발하며, 주로 해당 부분 개발.
//  (3) Data Layer : 데이터베이스 및 외부와의 연동 처리.

@RestController
@RequestMapping("/api")
public class RestApiController {

    // GetApi 작성.
    @GetMapping("/get/{id}")
    public void get(@PathVariable String id, @RequestParam String name){
    }

    // PostApi 작성.
    @PostMapping("/post")
    public void post(@RequestParam User user){
    }
    // => But, API 가 여러 개인 경우, 각 메소드마다 로그를 찍는 부분들을 하나로 모을 수 있다. => aop 패키지로 내용확인.

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        // db logic 이 1~2초 걸리는걸 가정함.
        Thread.sleep(1000 * 2);
    }
    // => 부가적인 내용들을 TimerAOP 에서 처리한다.
}