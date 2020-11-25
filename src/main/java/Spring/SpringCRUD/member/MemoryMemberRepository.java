package Spring.SpringCRUD.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository //인터페이스 구현을 위한 구현체 //데이터베이스가 아직 정해지지 않은 떄라서 임시로 생성

{
    private static Map<Long, Member> store = new HashMap<>(); //맴버정보를 저장하기위한 저장수 구현 Map<key type, value type> 이름 = new HashMap<key type, value type>();
                                                                //concurrent hashmap을 써야한다(동시성 이슈때문 찾아보기)

    //상속 : 상위클래스 메소드를 하위클래스에 덮어 씌운다
    @Override
    public void save(Member member) {
        store.put(member.getId(), member); // 위의 임시저장소에 해당 멤버의 key, value값을 저장한다

    }

    @Override
    public Member findbyId(Long memberId) {

        return store.get(memberId); //위의 임시저장소에서 memberId를 꺼내온다.
    }
}
