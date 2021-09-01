package ink.qs.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

//@ImportResource("classpath:spring.xml")
@ComponentScan(basePackages = {"ink.qs"})
@SpringBootApplication
public class EduStarter {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EduStarter.class, args);
//        System.out.println("run.getBean() = " + run.getBean("webApiConfig"));

    }
}
