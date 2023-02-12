package filter_and_interceptor.controller;
import filter_and_interceptor.annotation.AuthUser;
import filter_and_interceptor.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Filter(선/후 처리) : Client 로 부터 오는 요청/응답에 대해서 "최초/최종" 단계의 위치에 존재함.(Web Application 에서 관리되는 영역)
// [Filter -->> DispatcherServlet-->>HandlerInterceptor-->>AOP 동작]

// - Spring 에 의해서 데이터가 변환되기 전의 순수한 Client 의 요청/응답 값을 확인 할 수 있다.
// - 유일하게 ServletRequest, ServletResponse 의 객체를 변환할 수 있음.
// - 주로 Spring FrameWork 에서는 request / response 의 Logging 용도로 활용하거나,
//   인증과 관련한 Logic 들을 해당 Filter 에서 처리한다.

// Lombok
// - Lombok(롬복)은 Java 라이브러리로 반복되는 getter, setter, toString 등의 메서드 작성 코드를 줄여주는 코드 다이어트 라이브러리이다.

// @Slf4j : log 명령어 사용할 수 있음.

@AuthUser
@RestController
@RequestMapping("/api/user")
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