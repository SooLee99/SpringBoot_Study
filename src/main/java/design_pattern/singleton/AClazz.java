package design_pattern.singleton;

public class AClazz {
    // (1) 객체 생성
    private SocketClient socketClient;

    // (2) 기본 생성자를 통해 객체 초기화.
    // 문제점 : SocketClient 에서 디폴트 생성자를 막아놨기 때문에, new SocketClient()로 초기화 할 수 없다.
    // 해결방안 : SocketClient 클래스 안에 있는 getInstance() 이용.
    public AClazz(){
        this.socketClient = SocketClient.getInstance();
    }

    public SocketClient getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }
}