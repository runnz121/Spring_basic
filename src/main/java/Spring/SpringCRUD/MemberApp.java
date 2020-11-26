package Spring.SpringCRUD;

import Spring.SpringCRUD.member.Grade;
import Spring.SpringCRUD.member.Member;
import Spring.SpringCRUD.member.MemberService;
import Spring.SpringCRUD.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        //AppConfig를 선언하여 해당 객체를 주입
       // AppConfig appConfig = new AppConfig();
       // MemberService memberService = appConfig.memberService(); //appconfig로 인해 memverservice 인터페이스 호출(여기에는 memberserviceimpl이 있음)

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // 어노테이션 쓰기위해 사용 (매개변수로 AppConfig 넣으면 해당 클래스에있는 빈을 사용하겠다)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); //해당 빈을 쓰겠다(Appconfig의 쓸 매소드 이름, 타입)

        Member member = new Member(1L, "memberA", Grade.VIP); //해당 멤버를 저장
        memberService.join(member);

        Member findMember = memberService.findMember(1L); //1L아이디인 맴버를 호출
        System.out.println("new Member = " + member.getName()); //저장한 멤버
        System.out.println("findMember = " + findMember.getName()); //호출한 멤버
         }
}
