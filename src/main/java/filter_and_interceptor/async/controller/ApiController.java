package filter_and_interceptor.async.controller;
import filter_and_interceptor.async.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // Logger란?
    // - 시스템 운영에 대한 기록인 log를 남기는 것이 logger다.
    // - log를 잘 남기면, 오류가 발생했을때, 디버깅이 용이해지고, 시스템 에러가 어떤 부분에서 발생했는지를 추적해서 확인 할 수 있다.
    // - java.util.logging이라는 log package를 활용해서 파일이나 콘솔에 로그 내용을 출력할 수 있다.

    @Autowired
    // @Autowired : 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
    private TaskService taskService;


    @GetMapping("/hello")
    public String hello() {
        logger.info("Thread Start");

        for(int i = 0 ; i < 10; i ++){
            //async(i);
            taskService.run(i);
        }

        logger.info("Thread end");
        return "response hello";
    }

    @GetMapping("/future")
    public ListenableFuture<Integer> listenableFuture() throws InterruptedException, ExecutionException {
        logger.info("future Start");
        logger.info("future end");
        return taskService.listenableFuture(10000);
    }

    @GetMapping("/completableFuture")
    public CompletableFuture<String> completableFuture() throws Exception {
        logger.info("completableFuture Start");
        logger.info("completableFuture end");
        return taskService.completableFuture(1000);
    }

    @Async
    // @Async : 비동기 동작
    public void async(int i){
        logger.info("in method : {}",i);

    }
}