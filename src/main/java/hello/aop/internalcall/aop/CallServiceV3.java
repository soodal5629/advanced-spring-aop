package hello.aop.internalcall.aop;

import hello.aop.internalcall.InternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 구조 자체를 변경(분리)
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {

    private final InternalService internalService;
    public void external() {
        log.info("call external");
        internalService.internal(); // 내부 메서드 호출(this.internal())
    }
}
