package design_pattern.proxy;

// 브라우저에서 html 을 리턴하면 보여주는 형태.
public class Html {
    private String url;

    // 생성자에서 url을 받아서 html을 로딩하는 형태.
    public Html(String url){
        this.url = url;
    }
}