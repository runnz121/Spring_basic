package Spring.SpringCRUD.Scan.Filter;


import java.lang.annotation.*;

@Target(ElementType.TYPE) //Type : class 레벨에 붙는다
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {  //ComponentScan을 커스텀함



}
