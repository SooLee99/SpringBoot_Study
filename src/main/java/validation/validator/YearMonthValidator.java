package validation.validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import validation.annotation.YearMonth;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String pattern;

    // 초기화 시키는 메소드.
    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    // 검증하는 메소드.
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // yyyyMM 형식의 패턴이 들어왔는 지 확인.(true 반환)
        try{
            // LocalDate 는 년,월,일이 모두 들어가야 함.
            LocalDate localDate = LocalDate.parse(value+"01" , DateTimeFormatter.ofPattern(this.pattern));
        }catch (Exception e){
            return false;
        }


        return true;
    }
}