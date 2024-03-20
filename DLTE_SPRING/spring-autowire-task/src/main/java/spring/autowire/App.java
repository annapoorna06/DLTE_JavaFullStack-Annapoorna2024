package spring.autowire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "spring.autowire")
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        MyBank myBank = context.getBean(MyBank.class);
        myBank.execute();
        context.close();
    }
}
