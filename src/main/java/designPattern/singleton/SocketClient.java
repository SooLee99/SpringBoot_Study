package designPattern.singleton;

public class SocketClient {
    // Singleton 패턴 : 객체의 인스턴스가 오직 1개만 생성되는 패턴을 의미한다.

    // <Singleton 패턴 예시>
    // (1) Singleton은 자기 자신을 객체로 가지고 있어야 한다.
    private static SocketClient socketClient = null;

    // (2) 또한 기본 생성자를 private로 막아야 한다. => 새로운 객체생성을 막기 위해서이다.
    private SocketClient(){}

    // (3) static 메서드를 통해서 getInstance()를 제공해줘야 한다.
    public static SocketClient getInstance(){

        // (4) 현재 가지고 있는 객체가 null 값인 경우 생성.
        if(socketClient == null){
            socketClient = new SocketClient();
            System.out.println("socket new instance");
        }

        return socketClient;
    }

    public void connect(){
        System.out.println("socket");
    }

}