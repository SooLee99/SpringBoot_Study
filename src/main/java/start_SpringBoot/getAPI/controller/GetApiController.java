package start_SpringBoot.getAPI.controller;

import start_SpringBoot.getAPI.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// ▶ HTTP GET?
//  - HTTP POST 메서드는 특정한 리소스를 가져오도록 요청한다.
//  - GET 요청은 데이터를 가져올 때만 사용해야 한다.

// ▶ 주요 기능
//  - 조회 / 요청

// GET API (리소스 조회) => 리소스를 조회하고, 해당 도큐먼트에 대한 자세한 정보를 가져온다.
//      - 데이터를 요청할 때, 이 요청하는 데이터가 HTTP Request Message 의 Header 부분의 url 에 담겨서 전송된다.
//        그래서 요청시 url 상에 '?' 뒤에 데이터가 붙어 request 를 보내는 것이다.

@RestController
// @RestController = @Controller + @ResponseBody 가 합쳐진 형태로 "Json 형태"로 객체 데이터를 반환한다.

// @Controller : 사용자의 요청이 진입하는 시점.(대규모 서비스로 갈수록 처리해야 할 서비스가 많아지는데 중간에 Controller 가 중간제어자 역활을 함.)
// @ResponseBody : HTTP Body 안의 "JSON"을 VO에 맵핑하는 스프링 어노테이션.

@RequestMapping("/api/get")
// @RequestMapping : - 요청 주소(url) 설정, 요청 방식(GET, POST, DELETE, PATCH) 설정.
//                  → 요청이 들어왔을 때 어떤 콘트롤러가 호출되어야 하는지 알려주는 지표의 역활을 함.
public class GetApiController{
    @GetMapping(path =  "hello")
    // (1) @GetMapping : HTTP GET 요청을 특정 핸들러 메소드에 맵핑하기 위한 annotation. (주소에 파라미터가 노출)
    public String getHello(){
        return "get Hello";
    }

    // (2) 예전에 GET 요청을 주소맵핑을 할 때 사용했던 방법. (위의 문장과 동일한 문장.)
    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String hi(){
        return "hi";
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/path-variable/{name}")
    // (3) @PathVariable : URI 경로의 "일부를 파라미터"로 사용할 때 이용(URI 경로에서 값을 가져온다.)
    //      - 템플릿 변수의 값을 추출하고, 그 값을 메소드 변수에 할당하는데 사용된다.
    //      - Path : 경로
    //      - Variable : 변수
    public String pathVariable(@PathVariable(name = "name") String pathName){
        System.out.println("PathVariable : " +pathName);
        return pathName;
    }

    // (4) @RequestParam : URI 뒤에 붙는 모든 매개변수의 값을 가져올 때 사용한다.
    //      - Request : 브라우저에서 스트림을 얻을 때 사용.
    //      - Parameter : 매개변수
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){
        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry->{
            sb.append(entry.getKey() + " = " + entry.getValue()+"\n");
        });

        return sb.toString();
    }

    // (5) 파라미터가 명확한 경우.
    // @RequestParam : URI 뒤에 붙는 매개변수의 값을 가져올 때 사용한다.
    //      - Request : 브라우저에서 스트림을 얻을 때 사용.
    //      - Parameter : 매개변수
    @GetMapping("query-param02")
    public String queryParam02(@RequestParam String name, @RequestParam String email, @RequestParam String age){
        return name+" "+email+" "+age;
    }

    // (6) 파라미터를 객체로 받을 경우.
    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest){
        return userRequest.toString();
    }
}
