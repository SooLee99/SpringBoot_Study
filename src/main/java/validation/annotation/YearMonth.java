package validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import validation.validator.YearMonthValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// [YearMonth 라는 Annotation(유효성 검증 조건) 제작하기]
@Constraint(validatedBy = {YearMonthValidator.class})
// @Constraint : 사용자가 원하는 Constraint[제약조건](YearMonthValidator 클래스)와 Validation[검증](해당 클래스)을 만들어 이를 적용할 수 있다.

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
// @Target : 해당되는 타입으로 제약조건과 검증을 한다.

@Retention(RUNTIME)
// @Retention : 어노테이션이 언제까지 살아 남아 있을지를 정하는 것. (RUNTIME 으로 설정)
public @interface YearMonth {

    // 유효성 검증을 위반하면 이 문장을 출력할 예정임.
    String message() default "yyyyMM 형식에 맞지 않습니다.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    // 해당 패턴을 설정함.
    String pattern() default "yyyyMMdd";
}