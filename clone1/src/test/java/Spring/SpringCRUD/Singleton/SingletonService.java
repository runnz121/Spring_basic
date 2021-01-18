package Spring.SpringCRUD.Singleton;

public class SingletonService {

    //자기클래스를 private static 으로 선언함 = 클래스내에 오직 하나만 인스턴스 존재하게됨
    private static final SingletonService instance = new SingletonService(); //실행시 자기자신을 객체를 만들어(생성하여) instance 넣어놓는다.

    public static SingletonService getInstance(){ //조회시 오직 getInstance를 통해서만 가능
        return instance; //instance를 꺼낼 수 있는 유일한 로직 >>항상 같은 instatnce만 반환한다
    }

    private SingletonService(){ //외부에서 객체 생성 하지 못하도록 private으로 매소드 생성
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }



}
