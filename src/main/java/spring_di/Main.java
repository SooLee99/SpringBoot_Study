package spring_di;

public class Main {
    public static void main(String[] args) {
        // DI 예제 : 네이버에 책을 검색한다. (it 서적, 페이지 수 10, 사이즈 20, 책 이름 : spring-book )
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-book";

        // Base 64 encoding
        // DI : 매개변수를 통해 외부에서 주입을 받는다. (본질을 건드리지 않아도 이용이 가능하다.)
        Encoder encoder = new Encoder(new Base64Encoder());
        String result = encoder.encode(url);
        System.out.println(result);

    }
}
