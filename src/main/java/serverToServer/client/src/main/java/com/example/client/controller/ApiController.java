package serverToServer.client.src.main.java.com.example.client.controller;

import com.example.client.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serverToServer.client.src.main.java.com.example.client.service.RestTemplateService;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ApiController {

    // [Server to Server 간의 통신]
    // - 백엔드 서버가 클라이언트로서 다른 서버에게  요청을 할 때, RestTemplate 를 사용한다.
    // - spring3.0 부터 지원되었고 , 스프링에서 제공하는 http 통신에 유용하게 쓸 수 있는 템플릿이다 .
    private RestTemplateService restTemplateService;

    @Autowired
    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("hello")
    public UserResponse getHello(){
        return restTemplateService.hello();
    }

}
