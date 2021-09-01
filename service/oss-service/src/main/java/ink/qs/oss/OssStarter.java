package ink.qs.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"ink.qs"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OssStarter {
    public static void main(String[] args) {
        SpringApplication.run(OssStarter.class);
    }
}
