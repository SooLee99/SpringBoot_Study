package validation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import validation.annotation.YearMonth;

// ApiController 클래스 안에서 @RequestBody 객체를 통해서 User1 객체 정보를 입력받으면,
// @Valid 를 통해서 이 클래스 내부에서 설정된 유효성 검증을 검사해준다.
public class User1 {

    @NotBlank
    private String name;

    @Max(value = 90, message = "최대 나이는 90살 입니다.")
    // @Max : 객체의 최대 값을 설정해줌.
    private int age;

    @Email
    // @Email : e-mail 양식이어야 입력 처리가 가능해짐.
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    // @Pattern : 정규식(핸드폰 번호) 양식을 지정해줄 수 있다.
    private String phoneNumber;

    @YearMonth
    // 인터페이스를 제작하여 어노테이션을 별도로 처리할 수도 있다.
    // yyyyMM 형식의 유효성 규칙을 제작할 수 있다.
    private String reqYearMonth;

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
                '}';
    }
}