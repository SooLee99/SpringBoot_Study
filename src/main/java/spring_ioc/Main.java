package spring_ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
// @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan 을 합친 것이다.

// @Configuration : 현재 클래스가 Spring의 설정 파일임을 알려주는 어노테이션.
// @EnableAutoConfiguration : Spring boot 클래스패스 세팅 및 다양한 Bean 추가 등을 시켜주는 어노테이션.
// ComponentScan : 다른 컴포넌트, 서비스, 설정 등을 찾을 수 있게 도와주는 어노테이션.
//                 즉, 자바 클래스를 스프링 빈이라고 표시하고,
//                 스프링의 component-scanning 기술을 통해 ApplicationContext에 빈으로 등록하게 하는 역할.
public class Main {
    public static void main(String[] args) {
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-book";

        // (1) 스프링 애플리케이션이 실행되면,
        SpringApplication.run(Main.class, args);

        // (2) context를 가져온다.
        ApplicationContext context = ApplicationContextProvider.getContext();

        // (3) IoC : 이 예제 안에서 new 연산자를 찾을 수 없을 것이다.
        // => 모든 권한이 스프링으로 넘어갔기 때문이다.
        // Bean : 스프링 컨테이너에서 관리되는 객체.
        Encoder encoder = context.getBean("base64Encode", Encoder.class);
        String result = encoder.encode(url);
        System.out.println(result);
    }
}

// bean으로 직접 등록하는 방법
// @Configuration : 한 클래스 안에서 여러 개의 bean 을 설정할거라는 의미.
@Configuration
class AppConfig{

    @Bean("base64Encode")
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode")
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
}
