package design_pattern.proxy;

// 브라우저에서 캐시 기능을 지원하는 클래스.
// 캐시 : 데이터나 값을 미리 복사해 놓는 임시 저장소
public class BrowserProxy implements IBrowser {

    //  url과 html 캐싱
    private String url;
    private Html html;

    public BrowserProxy(String url){
        this.url = url;
    }

    @Override
    public Html show() {
        // 캐시가 null 이면, 캐시 안에 url 저장.
        if(html == null){
            this.html = new Html(url);
            System.out.println("BrowserProxy loading html from "+url);
        }

        // 캐시 안에 있는 값 실행.
        System.out.println("BrowserProxy use cache html");
        return html;
    }
}