package Spring.SpringCRUD.discount;

import Spring.SpringCRUD.member.Grade;
import Spring.SpringCRUD.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;


    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { //memeber에서 등급이 VIP인 경우 enum 은 ==비교이다
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
