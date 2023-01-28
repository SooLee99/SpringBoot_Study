package start_SpringBoot.putAPI.controller;

import start_SpringBoot.putAPI.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {
    @PutMapping("/put/{userId}")
    // @PutMapping : "주어진 URI 표현식과 일치하는 HTTP PUT 요청"을 처리합니다.

    // ▶ HTTP PUT?
    //  - HTTP PUT 메서드는 요청 페이로드를 사용해 새로운 리소스를 생성하거나, 대상 리소스를 나타내는 데이터를 대체함.

    // ▶ 주요 기능 (수정)

    // ▶ 정리
    //  - PutMapping 은 "멱등성"을 가지고, 여러 번 호출할 경우, 클라이언트가 받는 응답은 동일하다.
    //  - 대상 리소스를 나타내는 데이터가 있는지 없는지 체크하여 없을 경우 Created(201) 응답을 보내고,
    //  - 대상 리소스를 나타내는 데이터가 있을 경우 OK(200), No Content(204) 응답을 통해 성공적으로 처리되었음을 알린다.

    // ▶ 멱등성 : 동일한 요청을 한 번 보내는 것과 여러 번 연속으로 보내는 것이 같은 효과를 지님.

    public PostRequestDto put(@RequestBody PostRequestDto requestDto, @PathVariable(name = "userId") Long id){
        System.out.println(id);
        System.out.println(requestDto);
        return requestDto;
    }
}
