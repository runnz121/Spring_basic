package Spring.SpringCRUD.Order;

import Spring.SpringCRUD.discount.DiscountPolicy;
import Spring.SpringCRUD.discount.FixDiscountPolicy;
import Spring.SpringCRUD.discount.RateDiscountPolicy;
import Spring.SpringCRUD.member.Member;
import Spring.SpringCRUD.member.MemberRepository;
import Spring.SpringCRUD.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderServiceImpl implements OrderService {





    //이 코드로 인해 추상클래스와 구현체클래스 모두 의존하고 있음을 알 수 있다.  >>DIP 위반반
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //인터페이스dis에서 Fix 구현체를 불러와서 이를 discountpolicy 에 넣음
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //새로운 RateDiscountpolicy 적용

    private final MemberRepository memberRepository; //인터페이스만 의존
    //인터페이스에만 의존하는 코드로 변경 그러나 이렇게 되면 discountpolicy 에 아무런 값이 할당되어있지 않아 nullpointexception error 발생 >>page 27
    private final DiscountPolicy discountPolicy; //인터페이스만 의존

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { //final로 선언이 되었기 때문에(위의 2코드) 생성자가 필요하다.
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //OrderService 상속
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findbyId(memberId); //Member member :객체 참조 변수 객체가(member)생성 저장된 부분(Member)주소를 갖는다. >>회원 정보를 먼저조회함(memberId를 조회해서 나온 정보를 member 객체에 담음)
        int discountPrice = discountPolicy.discount(member, itemPrice); //해당 회원 정보를 할인 정책에 넘김(위에서 넘어온 member 객체와 item price를 받아 discountprice 에 저장

        return new Order(memberId, itemName, itemPrice, discountPrice); //주문 정보를 반환환
   }

   //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}


