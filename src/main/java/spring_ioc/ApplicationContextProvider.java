package spring_ioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    // applicationContext 매개변수 : 외부(스프링)로부터 데이터를 주입받는다.
    // 주입받은 데이터는 context 객체에 할당한다.
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    // 할당받은 context 를 반환하는 메서드.
    public static ApplicationContext getContext(){
        return context;
    }
}
