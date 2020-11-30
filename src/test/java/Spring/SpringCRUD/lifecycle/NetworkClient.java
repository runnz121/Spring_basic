package Spring.SpringCRUD.lifecycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct; //javax : java에서 공식적으로 지원한다는 뜻
import javax.annotation.PreDestroy;

public class NetworkClient {
        //implements InitializingBean, DisposableBean //초기화, 소멸인터페이스

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url =  " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call "+ url + "message =" + message);

    }
    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close =" +url);

    }

    @PostConstruct
    public void init() throws Exception { //의존관계 주입이 끝나면 호출 한다
        System.out.println("NetWorkCLient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() throws Exception { //빈이 종료될때 호출
        System.out.println("NetWorkClient.close");
        disconnect();

    }
}
