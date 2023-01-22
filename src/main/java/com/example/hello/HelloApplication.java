package com.example.hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;

//@SpringBootApplication
public class HelloApplication {
    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(HelloApplication.class, args);

        /*// Object Mapping
        var objectMapper = new ObjectMapper();

        // Response Object -> Json(text) => get 메서드를 사용한다는 것을 알 수 있다.
        var user = new User("steve", 10, "010-1111-1111");
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        // Json(text) -> Object => default 생성자를 필요로 한다.
        var objectUser = objectMapper.readValue(text, User.class);
        System.out.println(objectUser);
        */

    }

}
