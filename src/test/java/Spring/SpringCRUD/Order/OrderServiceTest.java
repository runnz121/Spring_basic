package Spring.SpringCRUD.Order;

import Spring.SpringCRUD.AppConfig;
import Spring.SpringCRUD.member.Grade;
import Spring.SpringCRUD.member.Member;
import Spring.SpringCRUD.member.MemberService;
import Spring.SpringCRUD.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;


    @BeforeEach//test실행전 무조건 실행한다는 뜻, test 갯수만큼 돌음
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    } //이 메소드로 인해 memberservice 객체가 생성되어 위의 생성자에 주입된다.



    @Test
    void createOrder() {
        //given
        Member member = new Member(1L, "MemberA", Grade.VIP);


        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
