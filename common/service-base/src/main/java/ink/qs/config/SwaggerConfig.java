package ink.qs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * groupName 分组名称
     * apiInfo 用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
     * paths 根据路径映射定义应包含哪个控制器的方法
     * @return
     */
//    @Bean
//    public Docket webApiConfig() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("webApi")
//                .apiInfo(webApiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("网站-课程中心API文档")
                .description("本文档描述了课程中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("MrQ", "https://github.com/MrQ-Coding",
                        "qs0906@163.com"))
                .build();
    }

    /**
     * 讲师模块api
     * @return
     */
    @Bean
    public Docket teacherDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                .groupName("teacherApi")
                .select()
                .paths(PathSelectors.ant("/eduservice/edu-teacher/**"))
                .build();
    }

    /**
     * 文件上传模块api
     * @return
     */
    @Bean
    public Docket fileUploadDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                .groupName("fileUploadApi")
                .select()
                .paths(PathSelectors.ant("/oss/file/**"))
                .build();
    }
}
