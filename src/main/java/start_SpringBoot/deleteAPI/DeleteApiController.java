package start_SpringBoot.deleteAPI;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    // delete -> 리소스 삭제를 의미함. (결과로 200을 리턴함.)
    @DeleteMapping("/delete/{userId}")
    // @DeleteMapping : 주어진 URI 표현식과 일치하는 HTTP Delete 요청을 처리합니다.
    
    // ▶ HTTP Delete?
    //  - HTTP Delete 는 서버에 있는 데이터를 삭제한다.
    //  - 데이터를 삭제하는 것이기 때문에 요청시에 Body 값과 Content-Type 값이 비워져있다.
    //  - URL을 통해서 어떠한 데이터를 삭제할지 파라메터를 받는다.
    //  - 데이터 삭제에 성공한다면 Body 값 없이 성공 응답만 보내게 된다.

    public void delete(@PathVariable String userId, @RequestParam String account){
        System.out.println(userId);     // 삭제된 User ID 출력.
        System.out.println(account);    // 삭제된 Account 출력.
    }
}
