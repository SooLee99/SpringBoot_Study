package start_SpringBoot.responseAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start_SpringBoot.responseAPI.dto.User;

@Controller // => html 페이지를 찾을 수 있음.
public class PageController {
    @RequestMapping("/main")
    public String main(){
        return "main.html"; // html 파일을 반환함.
    }

    // "html"에서 "json"을 넣어주는 방법.
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        // var 사용하는 이유 : 만약 긴 이름의 객체를 사용하게 된다면 편리하게 타입 추론이 가능한 var을 사용하는 것이 간편함.
        var user = new User();
        user.setName("steve");
        user.setAddress("패스트캠퍼스");
        return user;
    }
}
