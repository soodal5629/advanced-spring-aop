package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {
    private final ApplicationContext applicationContext;

    public CallServiceV2(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void external() {
        log.info("call external");
        // 지연해서 꺼내기 --> 빈에 등록된 프록시가 꺼내지겠찌?!
        CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        callServiceV2.internal(); // 내부 메서드 호출(this.internal())
    }

    public void internal() {
        log.info("call internal");
    }
}
