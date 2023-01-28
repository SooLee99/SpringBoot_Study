package start_SpringBoot.postAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

// 서버단에서는 카멜 케이스(Camel Case) 방식을 사용. - 첫 글자는 소문자로, 중간 글자들은 대문자로 시작하는 표기법 ex) phoneNumber
// 클라이언트단에서는 스네이크 케이스(Snake Case) 방식을 사용. - 언더바(_)가 포함된 표기법. ex) phone_number
public class PostRequestDto {
    private String account;
    private String email;
    private String address;
    private String password;
    @JsonProperty("phone_number")
    //  @JsonProperty : JSON 직렬화 시, 설정할 수 있는 이름을 지정하는 어노테이션이다.
    private String phoneNumber;

    @JsonProperty("OTP")
    private String OTP;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", OTP='" + OTP + '\'' +
                '}';
    }
}