package spring_aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

// AOP : Aspect Oriented Programming -> (관점 지향 프로그래밍)이라고 불린다.
//  - 관점 지향 : 쉽게 말해 어떤 로직을 기준으로 "핵심적인 관점", "부가적인 관점"으로 나누어서 보고, 그 관점을 기준으로 각각 모듈화하겠다는 것이다.

@Aspect
// @Aspect : "부가기능을 정의한 코드인 어드바이스(Advice)"와 "어드바이스를 어디에 적용하지를 결정하는 포인트컷(PointCut)"을 합친 개념이다.
// Aspect = PointCut + Advice
//  - 포인트컷(Pointcut) : 포인트컷은 어디에 부가 기능을 적용할지 판단하는 필터링 로직으로, 주로 클래스와 메서드 이름으로 필터링한다.
//                       어떤 포인트(Point)에 기능을 적용할지 하지 않을지 잘라서(cut) 구분하는 것이라 이해하면 된다.

//  - 어드바이스(Advice) : 횡단 관심에 해당하는 공통 기능의 코드, 독립된 클래스의 메소드로 작성한다.
//      <어드바이스의 동작 시점>
//      + Before	        메소드 실행 전에 동작
//      + After             메소드 실행 후에 동작
//      + After-returning	메소드가 정상적으로 실행된 후에 동작
//      + After-throwing	예외가 발생한 후에 동작
//      + Around	        메소드 호출 이전, 이후, 예외발생 등 모든 시점에서 동작

// 표현식 : 리턴타입 패키지경로 클래스명 메소드명(매개변수)
// 위빙(Weaving) : 포인트컷으로 지정한 핵심 관심 메소드가 호출될 때, 어드바이스에 해당하는 횡단 관심 메소드가 삽입되는 과정을 의미한다.
//                이를 통해 비즈니스 메소드를 수정하지 않고도 횡단 관심에 해당하는 기능을 추가하거나 변경이 가능해진다.

// 조인포인트(JoinPoint) : 어드바이스 메소드를 의미 있게 구현하려면 클라이언트가 호출한 비즈니스 메소드의 정보가 필요하다.
//                       어떤 메소드에서 어떤 동작이 이루어졌는지 알면 굉장히 편하다.
//                       스프링에서는 이런 정보들을 이용할 수 있도록 JoinPoint 인터페이스를 제공한다.
//      <조인포인트 인터페이스>
//      + Signature getSignature() : 클라이언트가 호출한 메소드의 시그니처(리턴타입, 이름, 매개변수) 정보가 저장된 Signature 객체 리턴
//      + Object getTarget() : 클라이언트가 호출한 비즈니스 메소드를 포함하는 비즈니스 객체 리턴
//      + Object[] getArgs() : 클라이언트가 메소드를 호출할 때 넘겨준 인자 목록을 Object 배열로 리턴
//      => 그 중에 1번 getSignature 메소드가 리턴하는 "Signature" 객체를 이용하면 호출되는 메소드의 대한 다양한 정보를 얻을 수 있다.

//      <Signature 객체가 제공하는 메소드>
//      + String getName() : 클라이언트가 호출한 메소드의 이름 리턴
//      + String toLongString() : 클라리언트가 호출한 메소드의 리턴타입, 이름, 매개변수를 패키지 경로까지 포함해서 리턴
//      + String toShortString() : 클라이언트가 호출한 메소드 시그니처를 축약한 문자열로 리턴


@Component
public class ParameterAop {

    @Before("cut()")
    // (1) @Before : AOP가 적용될 메소드가 실행되기 전의 시점을 말한다.
    //      - cut()메소드가 실행되는 시점에 이 메소드가 먼저 실행.
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // MethodSignature => 메소드 이름을 출력할 수 있다.
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs();
        for(Object obj : args){
            System.out.println("type : "+obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }
    }

    @Pointcut("execution(* spring_aop.controller..*.*(..))")            // => 표현식.
    // (2) @Pointcut : 조인 포인트 중에서 "어드바이스"가 적용될 위치를 선별하는 기능. (조인 포인트를 결정)
    //      - 스프링 AOP는 프록시 방식을 사용하므로 메소드 실행 지점만 포인트 컷으로 선별 가능.
    private void cut(){}

    @AfterReturning(value = "cut()", returning = "returnObj")
    // (3) @AfterReturning : AOP가 적용될 메소드가 에러없이 성공적으로 실행된 이후의 시점을 말한다.
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("return obj");
        System.out.println(returnObj);
    }
}