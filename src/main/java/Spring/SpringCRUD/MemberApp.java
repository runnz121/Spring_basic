package Spring.SpringCRUD;

import Spring.SpringCRUD.member.Grade;
import Spring.SpringCRUD.member.Member;
import Spring.SpringCRUD.member.MemberService;
import Spring.SpringCRUD.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP); //해당 멤버를 저장
        memberService.join(member);

        Member findMember = memberService.findMember(1L); //1L아이디인 맴버를 호출
        System.out.println("new Member = " + member.getName()); //저장한 멤버
        System.out.println("findMember = " + findMember.getName()); //호출한 멤버
         }
}
