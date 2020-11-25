package Spring.SpringCRUD.member;

public interface MemberService { //회원서비스도메인

    void join(Member member); //가입

    Member findMember(Long memberId); //조회
}
