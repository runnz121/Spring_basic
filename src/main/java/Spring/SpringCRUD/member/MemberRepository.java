package Spring.SpringCRUD.member;

public interface MemberRepository { //회원 맴버 저장 인터페이스 >> 구현체 필요

    void save(Member member);

    Member findbyId(Long memberId); //Member class 의 MemberId찾기

}
