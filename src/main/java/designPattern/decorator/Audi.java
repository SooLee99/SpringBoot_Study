package designPattern.decorator;

public class Audi implements ICar{

    private int price;

    // 생성자를 통해서 가격을 입력받는다.
    public Audi(int price){
        this.price = price;
    }

    // 가격을 반환하는 메서드.
    @Override
    public int getPrice(){
        return this.price;
    }

    // 금액을 출력하는 메서드.
    @Override
    public void showPrice() {
        System.out.println("Audi Base는 "+ price +" 원 입니다.");
    }
}