package start_SpringBoot.putAPI.controller;

import start_SpringBoot.putAPI.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {
    @PutMapping("/put/{userId}")
    public PostRequestDto put(@RequestBody PostRequestDto requestDto, @PathVariable(name = "userId") Long id){
        System.out.println(id);
        System.out.println(requestDto);
        return requestDto;
    }
}
