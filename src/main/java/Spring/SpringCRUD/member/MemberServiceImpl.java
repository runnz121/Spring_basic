package Spring.SpringCRUD.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService { //memberservice 구현체

    //가입 조회를 위한 MemberRepository interface 선언 = 해당 인터페이스의 구현객체를 선언(실제 조회를 위해)
    private final MemberRepository memberRepository; //인터페이스만 존재


                          //생성자에 이것을 작성해주면 >>자동적으로 의존관계를 주입해준다.(Component 는 자동으로 빈 등록을 해주기 때문에 의존관계를 설정할 수 없다 그래서 Autowired를 생성자에 작성하여 이를 해결)
    @Autowired            //ac.getBean(MemberRepository.class)가 Autowired로 인해 들어간다고 보면됨
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member); //호출시 다형성에 의하여 MemoryMemberRepository의 save기능이 호출된다 (즉 save > memberrepository(interface)> memorymemberrepositoryimpl save > interface > save 이런식)
                                        //다형성 : 부모인터페이스를 통해서(여기서는 MemberRepository 인터페이스) 형변환(업캐스팅), 상속, 구현을 함

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findbyId(memberId);
    }


    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }


}
