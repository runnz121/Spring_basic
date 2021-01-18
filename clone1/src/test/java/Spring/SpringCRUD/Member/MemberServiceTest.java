package Spring.SpringCRUD.Member;



import Spring.SpringCRUD.AppConfig;
import Spring.SpringCRUD.member.Grade;
import Spring.SpringCRUD.member.Member;
import Spring.SpringCRUD.member.MemberService;
import Spring.SpringCRUD.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class MemberServiceTest { //클래스에서 실행시켜야됨
    MemberService memberService;

    @BeforeEach//test실행전 무조건 실행한다는 뜻, test 갯수만큼 돌음
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    } //이 메소드로 인해 memberservice 객체가 생성되어 위의 생성자에 주입된다.


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