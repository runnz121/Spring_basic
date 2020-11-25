package Spring.SpringCRUD.Member;



import Spring.SpringCRUD.member.Grade;
import Spring.SpringCRUD.member.Member;
import Spring.SpringCRUD.member.MemberService;
import Spring.SpringCRUD.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;



class MemberServiceTest { //클래스에서 실행시켜야됨
    MemberService memberService = new MemberServiceImpl();


    @Test
    void join() {
        //given >>해당 정보가 주어짐
        Member member = new Member(1L, "memberA", Grade.VIP);


        //when >>리파짓에 해당 정보를 저장했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);


        //then >>불러온 정보조건이 저장된 정보조건과 일치하는가 확인
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}