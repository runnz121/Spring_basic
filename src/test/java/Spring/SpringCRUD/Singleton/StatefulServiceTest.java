package Spring.SpringCRUD.Singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    void statefulServiceSingleton(){

        //스프링 컨테이너 생성법
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000주문
        int userAprice = statefulService1.order("userA",10000);
        //ThreadB : Btkdydwk 20000주문
        int userBprice = statefulService2.order("userB",20000);

        //ThreadA : 사용자 A 주문 금액 조회
        //int price = statefulService1.getPrice();
        System.out.println("price = " + userAprice);//10000이 나오길 기대했찌만 20000나옴

        //이유 statefulService1 이나 2는 모두 statefulService이라는 같은 class 에서 return값을 반롼함
        //따라서 위 식에서 Thread A가 Thread B 로 대체되어 출려됨 즉 price는 같은 객체임으로 처음 10000 -> 나중에 들어온 20000으로 교체됨

        // Assertions.assertThat(statefulService1.userAprice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();

        }
    }

}