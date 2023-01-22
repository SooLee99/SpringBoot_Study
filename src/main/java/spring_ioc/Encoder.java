package spring_ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component
public class Encoder{
    private IEncoder iEncoder;

    // bean객체가 두 개인 경우(base64Encoder, urlEncoder)
    //      => 스프링이 결정을 못한다.
    // @Qualifier : 클래스를 지정하여 사용할 수 있다. @Qualifier("base64Encoder")

    public Encoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public void setIEncoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}
