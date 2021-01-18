package Spring.SpringCRUD.BeanDefinition;

import Spring.SpringCRUD.AppConfig;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    GenericXmlApplicationContext ac =  new GenericXmlApplicationContext("appConfig.xml");

        @Test
        @DisplayName("빈 설정 메타 정보 확인")
       public void findApplicationBean() {
            String[] beanDefinitionNames = ac.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

                if(beanDefinition.getRole() ==BeanDefinition.ROLE_APPLICATION){
                    System.out.println("beanDefinitionName = " + beanDefinitionName +
                            "beanDefinition =" + beanDefinition);
                }
            }
        }

    }

