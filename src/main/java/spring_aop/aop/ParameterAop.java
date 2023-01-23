package spring_aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

// AOP : 과거 프로그램의 규모가 커지면서 중복된 코드를 줄이고 유지보수성을 높이기 위해 OOP, 객체지향 프로그래밍이 등장하였다.
//       객체지향 프로그래밍은 각각의 역할을 분리하고 서로 필요할 기능을 호출하도록 하여 그 목표를 달성하였다.
//       웹서비스의 구조를 보면, 사용자 입장에서는 서로 다른 기능으로 보일지라도 여러 서비스와 레포지토리 객체를 공통으로 사용한다.

// @Aspect : "부가기능을 정의한 코드인 어드바이스(Advice)"와 "어드바이스를 어디에 적용하지를 결정하는 포인트컷(PointCut)"을 합친 개념이다.
// Aspect = Advice + PointCut
@Aspect
@Component
public class ParameterAop {

    // @Pointcut : 조인 포인트 중에서 어드바이스가 적용될 위치를 선별하는 기능. (조인 포인트를 결정)
    // - 스프링 AOP는 프록시 방식을 사용하므로 메소드 실행 지점만 포인트 컷으로 선별 가능
    @Pointcut("execution(* spring_aop.controller..*.*(..))")    // 적용시킬 위치.
    private void cut(){}

    // @Before : AOP가 적용될 메소드가 실행되기 전의 시점을 말한다.
    @Before("cut()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());
        Object[] args = joinPoint.getArgs();
        for(Object obj : args){
            System.out.println("type : "+obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }
    }

    // @AfterReturning : AOP가 적용될 메소드가 에러없이 성공적으로 실행된 이후의 시점을 말한다.
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("return obj");
        System.out.println(returnObj);
    }
}