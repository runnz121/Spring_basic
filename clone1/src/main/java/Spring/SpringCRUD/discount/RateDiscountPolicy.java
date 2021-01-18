package Spring.SpringCRUD.discount;

import Spring.SpringCRUD.annotation.MainDiscountPolicy;
import Spring.SpringCRUD.member.Grade;
import Spring.SpringCRUD.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@MainDiscountPolicy//내가 생성한 annotation을 설정
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {

            return 0;
        }
    }
}
