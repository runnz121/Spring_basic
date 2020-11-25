package Spring.SpringCRUD.Order;

public interface OrderService { //Order역활 구현
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
