package jUnit.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor // lombok - 자동 생성자 주입

public class MarketApi {

    public int connect(){
        return 1100;
    }
}