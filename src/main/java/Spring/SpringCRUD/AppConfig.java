package Spring.SpringCRUD;

import Spring.SpringCRUD.member.MemberService;
import Spring.SpringCRUD.member.MemberServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl();

    }


}
