package Spring.SpringCRUD;

import Spring.SpringCRUD.Order.Order;
import Spring.SpringCRUD.Order.OrderService;
import Spring.SpringCRUD.Order.OrderServiceImpl;
import Spring.SpringCRUD.member.Grade;
import Spring.SpringCRUD.member.Member;
import Spring.SpringCRUD.member.MemberService;
import Spring.SpringCRUD.member.MemberServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000); //클래스 참조객체 = 구현체.인터페이스(변수명)

        System.out.println("order = " + order); //Order 클래스의 toString 이 호출된다.

    }
}
