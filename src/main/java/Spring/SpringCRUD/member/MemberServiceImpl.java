package Spring.SpringCRUD.member;

public class MemberServiceImpl implements MemberService { //memberservice 구현체

    //가입 조회를 위한 MemberRepository interface 선언 = 해당 인터페이스의 구현객체를 선언(실제 조회를 위해)
    private final MemberRepository memberRepository = new MemoryMemberRepository();



    @Override
    public void join(Member member) {
        memberRepository.save(member); //호출시 다형성에 의하여 MemoryMemberRepository의 save기능이 호출된다 (즉 save > memberrepository(interface)> memorymemberrepositoryimpl save > interface > save 이런식)
                                        //다형성 : 부모인터페이스를 통해서(여기서는 MemberRepository 인터페이스) 형변환(업캐스팅), 상속, 구현을 함

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findbyId(memberId);
    }




}
