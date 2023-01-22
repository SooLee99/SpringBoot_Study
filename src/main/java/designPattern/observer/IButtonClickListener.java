package designPattern.observer;

// 리스너로부터 이벤트를 받는 클릭이벤트 클래스 
public interface IButtonClickListener {
    void clickEvent(String event);
}