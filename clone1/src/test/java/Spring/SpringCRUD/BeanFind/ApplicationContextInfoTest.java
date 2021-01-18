package Spring.SpringCRUD.BeanFind;

import Spring.SpringCRUD.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) { //iter 누르고 텝 누르면 for문 자동완성
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + "object = " + bean);

        }

    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {


        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { //iter 누르고 텝 누르면 for문 자동완성
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);


            //Role_Application : 직접 등록한 애플리케이션 빈
            //Role_INFRASTRUCTURE : 스프링이 등록한 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) ;
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + "object = " + bean);

        }
    }
}
