package hello.aop.member;

import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@ClassAop
@Component
@Slf4j
public class MemberServiceImpl implements MemberService {
    @Override
    @MethodAop("test value")
    public String hello(String param) {
        log.info("param = {}", param);
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }

}
