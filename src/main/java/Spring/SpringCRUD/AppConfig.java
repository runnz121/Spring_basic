package Spring.SpringCRUD;

import Spring.SpringCRUD.Order.OrderService;
import Spring.SpringCRUD.Order.OrderServiceImpl;
import Spring.SpringCRUD.discount.DiscountPolicy;
import Spring.SpringCRUD.discount.FixDiscountPolicy;
import Spring.SpringCRUD.discount.RateDiscountPolicy;
import Spring.SpringCRUD.member.MemberRepository;
import Spring.SpringCRUD.member.MemberService;
import Spring.SpringCRUD.member.MemberServiceImpl;
import Spring.SpringCRUD.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //환경설정이라는 뜻
public class AppConfig {


    //@Bean memberService -> new MemomryMemberRepository()
    //@Bean orderService -> new MemomryMemberRepository()
    //2번 호출 된다.

    @Bean //컨테이너에 저장됨
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //반환된 이것을 스프링 빈에 등록

    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }



    @Bean
    public DiscountPolicy discountPolicy(){
        System.out.println("AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }


}
