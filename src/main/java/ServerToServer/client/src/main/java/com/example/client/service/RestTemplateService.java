package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

// # 먼저 클라이언트 서버에서의 요청은 sevice 단에서 이루어 지며 UriComponentsBuider 를 통해 URI 를 생성하고,
//    RestTemplate를 생성해 요청을 만들어낼 수 있다.

@Service
public class RestTemplateService {

    // 1) get 방식의 통신을 요청하는 방법 [브라우저->서버(클라이언트)]
    // "http://localhost:9090/api/server/hello" <- 이 주소로 호출을 해서 response 를 받을 메소드.
    public UserResponse hello() {
        // (1) "http://localhost:9090/api/server/hello" <- 이 주소를 만든다.
        // - UriComponents 클래스는 말 그대로, URI 를 구성하는 Components 들을 효과적으로 다룰 수 있도록 하는 클래스이다.
        //      => UriComponents 클래스의 생성자는 모두 private 이기 때문에, 생성자를 직접 생성할 수 없다.

        // - UriComponentsBuilder 클래스는 UriComponents 를 Build 할 수 있도록 도와주는 클래스이다.
        //      => UriComponentsBuilder 클래스를 이용하여 UriComponents 의 인스턴스를 생성할 수 있습니다.

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "steve")
                .queryParam("age", 99)
                .encode()                                   // 파라미터 -> (안정적으로 uri 인코딩을 할 수 있다.)
                .build()
                .toUri();

        // (2) uri 가 잘 만들어졌는지 확인한다.
        System.out.println(uri.toString());

        // - RestTemplate 클래스(Spring 3부터 지원)는 REST API 호출이후 응답을 받을 때까지 기다리는 동기 방식이다.
        RestTemplate restTemplate = new RestTemplate();

        // getForEntity == 주어진 URL 에서 ResponseEntity 타입으로 값을 반환받음.
        // 헤더내용과 상세정보를 받기 위해서는 ResponseEntity 객체로 URL 정보를 받는 것을 추천함.
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        // (3) getStatusCode() 와 response 안의 Body 내용을 출력한다.
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    // 2) post 방식의 통신을 하는 방법 [브라우저->서버(클라이언트)]
    // "http://localhost:9090/api/server/user" <- 이 주소로 호출을 해서 user 을 등록시키는 메소드.
    public UserResponse post() {
        // (1) http://localhost:9090/api/server/user/{userId}/name/{userName}  <- 이 주소를 만든다.

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();

        // (2) uri 가 잘 만들어졌는지 확인한다.
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        // (3) RestTemplate post 전송.
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }

    public UserResponse exchange() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);
        return response.getBody();

    }

    public Req<UserResponse> genericExchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http body json

        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(10);

        Req<UserRequest> req = new Req<UserRequest>();
        req.setHeader(
                new Req.Header()
        );
        req.setResBody(
                userRequest
        );

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Req<UserResponse>> response = restTemplate
                .exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});

        return response.getBody();
    }

}
