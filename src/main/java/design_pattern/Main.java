package design_pattern;

import design_pattern.adapter.*;
import design_pattern.aop.AopProxy;
import design_pattern.aop.IAopBrowser;
import design_pattern.decorator.*;
import design_pattern.facade.SftpClient;
import design_pattern.observer.IButtonClickListener;
import design_pattern.observer.MyButton;
import design_pattern.proxy.BrowserProxy;
import design_pattern.proxy.IBrowser;
import design_pattern.singleton.AClazz;
import design_pattern.singleton.BClazz;
import design_pattern.strategy.Base64Strategy;
import design_pattern.strategy.Encoder;
import design_pattern.strategy.NormalStrategy;

import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main");

        // 1. Singleton 패턴 예시
        // Singleton 패턴 : 객체의 인스턴스가 오직 1개만 생성되는 패턴을 의미한다.
        AClazz a = new AClazz();
        BClazz b = new BClazz();

        System.out.println("A 객체와 B 객체는 동일한 객체인가?");
        System.out.println(a.getSocketClient().equals(b.getSocketClient()));

        // 2. Adapter 패턴 예시
        // Adapter 패턴 : 호환성이 없는 기존 클래스의 인터페이스를 변환하여 사용자가 기대하는 인터페이스 형태로 변환시키는 패턴을 의미한다.
        // (1) 헤어드라이기(110v) ON
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        // (2) 청소기(220v) 제품 가져오기.
        Cleaner cleaner = new Cleaner();

        // (3) 에어컨(220v) 제품 가져오기.
        AirConditioner airConditioner = new AirConditioner();

        // (3) 110v 어뎁터 안에 220v 제품(청소기, 에어컨) 입력받기.
        Electronic110V _cleaner = new SocketAdapter(cleaner);
        Electronic110V _airConditioner = new SocketAdapter(airConditioner);
        connect(_cleaner);
        connect(_airConditioner);

        // 3. Proxy 패턴 예시
        // Proxy 패턴 : 실제 기능을 수행하는 객체를 대신하여 가상의 객체(Proxy Object)를 사용해 로직의 흐름을 제어하는 디자인 패턴이다.
        // 캐시 : 데이터나 값을 미리 복사해 놓는 임시 저장소.
        
        // (1) 브라우저에서 url을 입력해서 html을 리턴.
        // (2) 리턴할 때 캐시에 url이 저장되어 있으면 추가적인 객체생성을 방지함.        
        IBrowser Browser = new BrowserProxy("www.naver.com");
        Browser.show();Browser.show();Browser.show();Browser.show();Browser.show();
        // 결과 : 캐시를 이용해서 객체는 한 번만 생성되어 저장하는 것을 알 수 있다.
        // => html 로딩 시간 단축.

        AtomicLong startTime = new AtomicLong();
        AtomicLong endTime = new AtomicLong();
        IAopBrowser aopBrowser = new AopProxy(
                "www.google.com",
                () -> {
                    System.out.println("before");
                    startTime.set(System.currentTimeMillis());
                },
                () -> {
                    System.out.println("after");
                    endTime.set(System.currentTimeMillis() - startTime.get());
                }
        );
        aopBrowser.show();
        System.out.println(endTime + " ms");
        // 결론 : 캐시를 이용하여 로딩시간 단축을 확인할 수 있다.
        // aop 패턴은 proxy 패턴을 이용하는 것을 알 수 있다.

        // 4. decorator 패턴 예시
        // decorator 패턴 : 기존 클래스는 유지하되, 이후 필요한 추가기능을 조합하는 패턴이다.
        // 아우디의 등급을 올릴 때마다 가격이 올라가는 코드.
        ICar audi = new Audi(500);
        audi.showPrice();           // 아우디의 가격은 500원입니다.

        ICar audi3 = new A3(audi);
        audi3.showPrice();           // 아우디의 가격은 1000원입니다.

        ICar audi4 = new A4(audi);
        audi4.showPrice();           // 아우디의 가격은 2000원입니다.

        ICar audi5 = new A5(audi);
        audi5.showPrice();           // 아우디의 가격은 3000원입니다.

        // 5. observer 패턴 예시
        // observer 패턴 : 옵저버들의 목록을 객체(관찰하려는 대상)에 등록하여 객체가 상태 변화가 있을 때마다
        // 메서드 등을 통해 객체가 직접 목록의 각 옵저버들에게 통지하는 디자인 패턴이다.

        MyButton button = new MyButton("종료 버튼");
        IButtonClickListener listener = event -> System.out.println("click event : "+event);
        button.addListener(listener);

        button.click("한번 클릭");
        button.click("두번 클릭");
        button.click("세번 클릭");

        // 6.

        SftpClient client = new SftpClient("www.google.com", 22, "/home/content", "content.tmp");
        client.connect();

        String content = "content";
        client.write(content);
        String result = client.read();
        System.out.println("----- 내용 : "+result+" -----");

        client.disConnect();

        Encoder base64Encoder = new Encoder();
        base64Encoder.setEncodingStrategy(new Base64Strategy());
        System.out.println(base64Encoder.getMessage("message"));

        Encoder normalEncoder = new Encoder();
        normalEncoder.setEncodingStrategy(new NormalStrategy());
        System.out.println(normalEncoder.getMessage("message"));

    }

    // (2) adapter 패턴 예시 메서드 (콘센트 역활)
    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }
}