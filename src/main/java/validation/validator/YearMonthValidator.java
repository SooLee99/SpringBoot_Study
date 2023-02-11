package validation.validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import validation.annotation.YearMonth;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// YearMonthValidator 클래스 : 유효성 검증 조건을 검사하는 클래스[ConstraintValidator<YearMonth, String>을 상속.]
public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String pattern;

    // 유효성 검증 조건을 초기화하는 생성자.
    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    // 유효성을 검증하는 메소드.
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // yyyyMM 형식의 패턴이 들어왔는 지 확인.(true 반환)
        try{
            // LocalDate 는 년,월,일이 모두 들어가야 하기 때문에 +"01"을 설정함.
            LocalDate localDate = LocalDate.parse(value+"01" , DateTimeFormatter.ofPattern(this.pattern));
        }catch (Exception e){
            return false;   // 위 문장 실행 시 에러가 발생한다면 false 출력.
        }
        return true;        // 위 문장 실행 시 에러가 발생한지 않는다면 true 출력.
    }
}