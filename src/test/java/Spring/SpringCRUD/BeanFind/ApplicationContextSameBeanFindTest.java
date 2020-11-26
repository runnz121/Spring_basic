package Spring.SpringCRUD.BeanFind;

import Spring.SpringCRUD.AppConfig;
import Spring.SpringCRUD.member.Member;
import Spring.SpringCRUD.member.MemberRepository;
import Spring.SpringCRUD.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.spliterator;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameConfig.class);

    /**@Test
    @DisplayName("같은 타입으로조회시 둘이상있으면 중복오류 발생")
    void findBeanByTypeDuplicate() {
        MemberRepository bean = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));

    }*/

    @Test
    @DisplayName("타입조회시 같은 타입 둘 이상이면 빈 이름 지정")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 모두 조회")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));

        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }




    @Configuration//해당클래스 안에서 쓸 config 파일 생성성(static이라고 선언시 해당 클래스 안에서만 사용)
    static class SameConfig{


        //객체의타입은 다르고 클래스 타임이 같을 수 있는 경우 생성
        @Bean
       public MemberRepository memberRepository1 () {
            return new MemoryMemberRepository();

        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }



    }

}
