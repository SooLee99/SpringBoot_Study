package spring_aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

// TimeAop : 특정 메소드의 실행시간을 찍을 클래스.

// @Component와 @Bean의 차이점
// @Bean은 "메소드 레벨"에서 선언하며, 반환되는 객체(인스턴스)를 개발자가 수동으로 빈으로 등록하는 애노테이션이다.
// @Component는 "클래스 레벨"에서 선언함으로써 스프링이 런타임시에 컴포넌트스캔을 하여 자동으로 빈을 찾고(detect) 등록하는 애노테이션이다.
@Aspect
@Component
public class TimerAop {

    @Pointcut("execution(* spring_aop.controller..*.*(..))")
    private void cut(){}

    @Pointcut("@annotation(spring_aop.annotation.Timer)")
    private void enableTimer(){}

    @Around("cut() && enableTimer()")   
    // @Around : 콘트롤러 패키지의 모든 메서드의 호출 이전, 이후, 예외발생 등 모든 시점에서 동작
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 반복적인 객체생성을 한 곳으로 모을 수 있다.
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();
        // - Around 에서 joinPoint.proceed() 에 대한 시점이 중요하다.
        //      proceed()를 기준으로 위의 소스는 before(메소드 실행 전에 동작),
        //      아래의 소스는 afterThrowing(예외가 발생한 후에 동작)로 구분 지어질 수 있다.

        //      proceed() 의 리턴 값은 Object 이다. 이는 Aspect 로 연결된 Original Method 의 리턴 값을 받을 수 있다. ( 형 변환 )

        stopWatch.stop();
        System.out.println("total time : "+stopWatch.getTotalTimeSeconds());
    }
}