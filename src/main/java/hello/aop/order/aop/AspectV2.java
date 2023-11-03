package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    // 포인트컷 분리 및 하나의 포인트컷 표현식을 여러 어드바이스에서 함께 사용 가능
    // hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder() {} // pointcut signature == allOrder()

    @Around("allOrder()") // 포인트컷
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // advice
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니쳐
        return joinPoint.proceed(); // target 호출
    }
}
