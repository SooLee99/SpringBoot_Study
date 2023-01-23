package design_pattern.proxy;

public class Browser implements IBrowser {
    private String url;

    // 브라우저에서 url 을 받아서 url 을 저장.
    public Browser(String url){
        this.url = url;
    }

    @Override
    public Html show() {
        System.out.println("Browser loading html from "+url);
        return new Html(url);   // 새로운 html을 반환시켜줌.
    }
}