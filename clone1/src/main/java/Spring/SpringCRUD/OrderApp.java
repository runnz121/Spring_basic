package Spring.SpringCRUD;

import Spring.SpringCRUD.Order.Order;
import Spring.SpringCRUD.Order.OrderService;
import Spring.SpringCRUD.Order.OrderServiceImpl;
import Spring.SpringCRUD.member.Grade;
import Spring.SpringCRUD.member.Member;
import Spring.SpringCRUD.member.MemberService;
import Spring.SpringCRUD.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {


//        AppConfig appConfig= new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //Annotation기반으로 스프링컨테이너를 만들어라( XML기반도 있음)

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000); //클래스 참조객체 = 구현체.인터페이스(변수명)

        System.out.println("order = " + order); //Order 클래스의 toString 이 호출된다.

    }
}
