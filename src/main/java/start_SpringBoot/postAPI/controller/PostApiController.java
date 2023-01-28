package start_SpringBoot.postAPI.controller;

import org.springframework.web.bind.annotation.*;
import start_SpringBoot.postAPI.dto.PostRequestDto;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

    @PostMapping("/post1")
    // @PostMapping : 주어진 URI 표현식과 일치하는 HTTP POST 요청을 처리합니다.

    // ▶ HTTP POST?
    //  - HTTP POST 메서드는 서버로 데이터를 전송한다.
    //  - POST 요청은 보통 "HTML 양식을 통해 서버에 전송"하며, 서버에 변경사항을 만든다.

    // ▶ 주요 기능 (추가/등록)
    //  - 기존 리소스에 주석달기
    //  - 게시판, 뉴스, 그룹, 메일링 리스트나 이와 유사한 시스템에 글 올리기
    //  - 회원가입 모달로 새로운 사용자 추가하기
    //  - 양식 전송 결과 등 데이터 블록을 데이터 처리 프로세스에 보내기
    //  - 이어붙이기 연산을 통한 데이터베이스 확장

    public void post1(@RequestBody Map<String, Object> requestData) {
        requestData.forEach((key, value) -> {
            System.out.println("key : " + key);
            System.out.println("key : " + value);
        });
    }

    @PostMapping("/post2")
    public void post2(@RequestBody PostRequestDto requestData) {
        System.out.println(requestData);

        requestData.getAccount();
        requestData.getEmail();
        requestData.getPassword();
        requestData.getAddress();
        requestData.getPhoneNumber();
    }
}
