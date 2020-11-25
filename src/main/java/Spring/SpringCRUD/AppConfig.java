package Spring.SpringCRUD;

import Spring.SpringCRUD.Order.OrderService;
import Spring.SpringCRUD.Order.OrderServiceImpl;
import Spring.SpringCRUD.discount.FixDiscountPolicy;
import Spring.SpringCRUD.member.MemberService;
import Spring.SpringCRUD.member.MemberServiceImpl;
import Spring.SpringCRUD.member.MemoryMemberRepository;

public class AppConfig { //환경설정은 모두 여기서 지정한다

    //memberservice 구현
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository()); //생성자 주입(new로 생성된 생성자를 통해서 주입)
                                                                    // memberService로 호출이 들어오면 MemberServiceImpl의 MemoryMemberRepository객체에 할당이 된다.

    }

    //orderservice 구현
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy()); //여기는 memberRepository와 discountPolicy 2개의 field가 존재
    }


}
