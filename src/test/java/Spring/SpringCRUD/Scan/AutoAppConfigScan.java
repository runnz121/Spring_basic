package Spring.SpringCRUD.Scan;

import Spring.SpringCRUD.AutoAppConfig;
import Spring.SpringCRUD.member.Member;
import Spring.SpringCRUD.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigScan {

    @Test
    public void basicScan() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
