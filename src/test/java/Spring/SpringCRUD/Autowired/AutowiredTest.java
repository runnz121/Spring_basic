package Spring.SpringCRUD.Autowired;

import Spring.SpringCRUD.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption (){
      ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {

        @Autowired(required = false) //자동주입 대상이 없기 때문에 호출 자체가 안된다.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable  Member noBean2) { //호출은 되지만 null값 반환
            System.out.println("noBean2 = " + noBean2);

        }

        @Autowired
        public void setNoBean1(Optional<Member> noBean3) { //스프링빈 없을시 optional.empty로 반환
            System.out.println("noBean3 = " + noBean3);

        }


    }
}
