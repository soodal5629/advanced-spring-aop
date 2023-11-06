package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Order {
    // @Order는 Aspect(혹은 class) 단위로 순서 적용 가능

    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("hello.aop.order.aop.Pointcuts.allOrder()") // 포인트컷
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            // advice
            log.info("[log] {}", joinPoint.getSignature()); // join point 시그니쳐
            return joinPoint.proceed(); // target 호출
        }
    }

    @Aspect
    @Order(1)
    public static class TxAspect {
        // hello.aop.order 패키지와 하위 패키지이면서 + 클래스 이름 패턴이 *Service
        @Around("hello.aop.order.aop.Pointcuts.orderAndService()") // 포인트컷 조합
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

}
