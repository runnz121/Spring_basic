package Spring.SpringCRUD;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(  //이게 붙은 class는 자동으로 bean 등록해준다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //해당 Annotation은 등록을 제외시킨다.
)
public class AutoAppConfig {








}
