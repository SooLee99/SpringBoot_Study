package ilter_and_interceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FilterandinterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterandinterceptorApplication.class, args);
    }

}