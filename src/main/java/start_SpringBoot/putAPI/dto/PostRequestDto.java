package start_SpringBoot.putAPI.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
// @JsonNaming : 전체 멤버변수를 새로 네이밍 해준다.(내부적으로 보면 대문자가 나오는 자리에 _와 소문자로 바꿔 넣어주는 간단한 구조를 가지고 있다.)
public class PostRequestDto {
    private String name;
    private String age;

    private List<CarDto> carList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<CarDto> getCarList() {
        return carList;
    }

    public void setCarList(List<CarDto> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", carList=" + carList +
                '}';
    }
}
