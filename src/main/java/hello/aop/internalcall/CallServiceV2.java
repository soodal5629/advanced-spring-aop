package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {
    // ApplicationContext는 너무 거대하고 기능이 많음
    //private final ApplicationContext applicationContext;

    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

//    public CallServiceV2(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }

    public void external() {
        log.info("call external");
        // 지연해서 꺼내기 --> 빈에 등록된 프록시가 꺼내지겠찌?!
        //CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);

        // 지연해서 꺼내기 --> 스프링 빈 생성시점이 아니라 getObject()를 호출하는 스프링 컨테이너에서 빈을 조회
        CallServiceV2 callServiceV2 = callServiceProvider.getObject();
        callServiceV2.internal(); // 내부 메서드 호출(this.internal())
    }

    public void internal() {
        log.info("call internal");
    }
}
