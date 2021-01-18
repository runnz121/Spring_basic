package Spring.SpringCRUD.Scan.Filter;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

public class componentFilterAppConfigTest {

    @Test
    public void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class); //beanA는 Include로 설정
        assertThat(beanA).isNotNull();



        Assertions.assertThrows( //jUnit으로 설정
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)); //beanB는 Exclude로 설정
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =MyIncludeComponent.class), //type = Filtertype은 생략해도된다.
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =MyExcludeComponent.class)

    )
    static class ComponentFilterAppConfig {

    }




}
