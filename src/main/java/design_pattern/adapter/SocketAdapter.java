package design_pattern.adapter;

// 220v -> 110v로 변환해주는 클래스
// (1) 110v로 반환을 해줘야 하기 때문에 기본적으로 110v로 상속받음.
public class SocketAdapter implements Electronic110V {

    // (2) 연결시켜줄 220v 객체로 가져오기.
    private Electronic220V electronic220V;

    // (3) 생성자를 통해서 220v 제품을 가져오기.
    public SocketAdapter(Electronic220V electronic220V){
        this.electronic220V = electronic220V;
    }

    // (4) 110v에 220v 제품이 연결이 되면 110v의 connect()를 호출. (220v -> 110v)
    @Override
    public void powerOn() {
        electronic220V.connect();
    }
}