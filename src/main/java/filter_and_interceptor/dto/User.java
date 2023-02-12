package filter_and_interceptor.dto;


import lombok.*;

// [Lombok] : Java 라이브러리로 반복되는 getter, setter, toString 등의 메서드 작성 코드를 줄여주는 코드 다이어트 라이브러리이다.
@Data
// @Getter / @Setter, @ToString, @EqualsAndHashCode와 @RequiredArgsConstructor 를 합침.
// Getter 와 Setter 를 직접 만들지 않아도 된다.

@NoArgsConstructor
// 기본 생성자를 생성해준다.

@AllArgsConstructor
// 전체 변수를 생성하는 생성자를 만들어준다.

public class User {

    private String name;
    private int age;

}