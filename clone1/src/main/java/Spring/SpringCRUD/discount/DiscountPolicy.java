package Spring.SpringCRUD.discount;

import Spring.SpringCRUD.member.Member;

public interface DiscountPolicy {

    /**
     *
     * return 할인대상금액
     *      */

    int discount(Member member, int price);
}
