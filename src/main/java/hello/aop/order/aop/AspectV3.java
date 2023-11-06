package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    // 포인트컷 분리 및 하나의 포인트컷 표현식을 여러 어드바이스에서 함께 사용 가능
    // hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder() {} // pointcut signature == allOrder()

    // 트랜잭션 => 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {}

    @Around("allOrder()") // 포인트컷
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // advice
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니쳐
        return joinPoint.proceed(); // target 호출
    }

    // hello.aop.order 패키지와 하위 패키지이면서 + 클래스 이름 패턴이 *Service
    @Around("allOrder() && allService()") // 포인트컷 조합
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
}
