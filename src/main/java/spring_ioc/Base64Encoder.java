package spring_ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

// IoC : 직접 객체를 주입하는 것이 아닌, Spring Container 가 관리한다.
@Component
// @Component : Bean Configuration 파일에 Bean을 따로 등록하지 않아도 사용할 수 있다. (자동 주입 어노테이션)
// "이 클래스는 스프링이 알아서 관리해줘!" 라는 명령어. <= IntelliJ 가 알아서 찾아줌.
public class Base64Encoder implements IEncoder {
    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
