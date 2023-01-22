package startSpringBoot.responseAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    /*
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User();
        user.setName("steve");
        user.setAddress("패스트캠퍼스");
        return user;
    }*/
}
