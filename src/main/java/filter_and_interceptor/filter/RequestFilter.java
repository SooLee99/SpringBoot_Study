package filter_and_interceptor.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@WebFilter(urlPatterns = "/api/*")
public class RequestFilter implements Filter {
    // (1) Filter : 요청(Request)과 응답(Response)에 대한 정보들을 변경할 수 있게 개발자들에게 제공하는 서블린 컨테이너이다.
    // - 필터는 스프링 컨텍스트 외부에 존재하여 스프링과 무관한 자원에 대해 동작한다.

    // (2) Interceptor : 인터셉터는 스프링의 DistpatcherServlet 이 컨트롤러를 호출하기 전, 후로 끼어들기 때문에
    //               스프링 컨텍스트(Context, 영역) 내부에서 Controller(Handler)에 관한 요청과 응답에 대해 처리한다.
    // - 인터셉터의 역활 : 인터셉터는 여러 개를 사용할 수 있고 로그인 체크, 권한체크, 프로그램 실행시간 계산작업 로그확인 등의 업무처리.


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        // 전처리(request) 객체
        // - ContentCachingRequestWrapper 클래스 : ContentCachingRequestWrapper 안에 있는 ByteArrayOutputStream 에
        //                                        내용을 미리 담아두는데 뒤에서 누가 읽을때 여기 담겨진 내용들을 뒤에서 읽을 수 있게 해준다.
        ContentCachingRequestWrapper wrappingRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        
        // 후처리(response) 객체
        ContentCachingResponseWrapper wrappingResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(wrappingRequest, wrappingResponse);

        // 전처리 출력
        System.out.println("---req ---");
        System.out.println(new String(wrappingRequest.getContentAsByteArray(),"UTF-8"));
        byte[] b = wrappingRequest.getContentAsByteArray();
        System.out.println("---req ---");

        // 후처리 출력
        System.out.println("---res ---");
        System.out.println(new String(wrappingResponse.getContentAsByteArray(),"UTF-8"));
        System.out.println("---res ---");
        wrappingResponse.copyBodyToResponse();
    }
}