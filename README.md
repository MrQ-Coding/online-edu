# 一、特色

## 1、统一接口

项目中我们会将响应封装成json返回，一般我们会将所有接口的数据格式统一， 使前端(iOS Android,Web)对数据的操作更一致、轻松。一般情况下，统一返回数据格式没有固定的格式，只要能描述清楚返回的数据状态以及要返回的具体数据就可以。但是一般会包含状态码、返回消息、数据这几部分内容。

```json
//模板
{
 "success": boolean, //响应是否成功
 "code": int, //响应码
 "message": String, //返回消息
 "data": HashMap //返回数据，放在键值对中
}
```



```json
//列表
{
 "success": true,
 "code": 20000,
 "message": "成功",
 "data": {
     "items": [
         {
             "id": "1",
             "name": "xxx",
             "intro": "毕业于师范大学数学系，热爱教育事业，执教数学思维6年有余"
         }
     ]
 }
}

//分页
{
 "success": true,
 "code": 20000,
 "message": "成功",
 "data": {
     "total": 1,
     "rows": [
         {
             "id": "1",
             "name": "刘德华",
             "intro": "毕业于师范大学数学系，热爱教育事业，执教数学思维6年有余"
         }
     ]
 }
}

//no-data
{
 "success": true,
 "code": 666,
 "message": "成功",
 "data": {}
}

{
 "success": false,
 "code": 555,
 "message": "失败",
 "data": {}
}
```

## 2、Swagger

> 优点：前期是单人开发，后续是合作开发，因为接口交接比较麻烦，测试也很麻烦，使用swagger来测试，再接口统一接口，省去前后端接口交接的时间，也能实时更新api文档，方便快捷

- 定义在类上：`@Api`

- 定义在方法上：`@ApiOperation`

- 定义在参数上：`@ApiParam`

- 忽略的Api：`@ApiIgnore`

Swagger作为组件加入Springboot：

`+ `  依赖（第三方依赖）

```xml
<dependency>
    <groupId>com.spring4all</groupId>
    <artifactId>swagger-spring-boot-starter</artifactId>
    <version>2.0.0.RELEASE</version>
</dependency>
```

`+ `  配置

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("网站-课程中心API文档")
                .description("本文档描述了课程中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("MrQ", "https://github.com/MrQ-Coding",
                        "qs0906@163.com"))
                .build();
    }
}
```

`*` `Swagger`的`api`功能对于生产项目来说是可有可无的。我们`Swagger`往往是用于测试环境供项目前端团队开发或供别的系统作接口集成使上。系统上线后，很可能在生产系统上隐藏这些`api`列表。 但如果配置是通过`@Configuration`注解写死在`java`代码里的话，那么上线的时候想去掉这个功能的时候，那就尴尬了，不得不修改java代码重新编译。通过`spring`最传统的xml文件配置方式写一个类似于`<bean class="xxxxx"/>`这样的`bean`配置到`spring`的`xml`配置文件中。然后通过`@ImportResource`引入。

`*` 接口分组：接口过多时，可以定义多个docket，不同功能分为不同的组别，通过每个Docket的`groupName()`方法指定组名即可

## 3、统一异常处理

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Re globalExceptionHandler(Exception e) {
        return Re.error().message(e.getMessage());
    }
}
```

- 使用`@ControllerAdvice`和`@ExceptionHandler`指定要捕获的异常，结果显示则是沿用自定义的返回类
- 其他实现方法（自定义springboot的异常处理类，`@ConditionalOnMissingBean( value = {ErrorController.class},search = SearchStrategy.CURRENT)`）

**测试：**

```java
@ApiOperation("测试")
@PutMapping("test")
public Re test(){
    try {
            i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
        } 
   return Re.ok().data("item",i);
}

/*
* 返回结果如下，返回的是自己的异常处理结果
* {
*  "success": false,
*  "code": 555,
*  "message": "Required URI template variable 'id' for method parameter type String  *  is not present",
*  "data": {}
* }
*/
```

## 4、对象存储云服务--OSS

> 为了解决海量数据存储与弹性扩容，项目中我们采用云存储的解决方案- 阿里云OSS。



## 5、常量读取

> 阿里云填写`ACCESS_KEY_ID`的时候，从`application.properties`文件中读取属性，通过`@Value`注入到类中的属性中。

1、Spring为bean提供了两种初始化bean的方式，实现InitializingBean接口，实现afterPropertiesSet方法，或者在配置文件中通过init-method指定，两种方式可以同时使用。

2、实现InitializingBean接口是直接调用afterPropertiesSet方法，比通过反射调用init-method指定的方法效率要高一点，但是init-method方式消除了对spring的依赖。

3、如果调用afterPropertiesSet方法时出错，则不调用init-method指定的方法。

# 二、问题

-  class path resource [springfox/documentation/swagger2/configuration/Swagger2DocumentationConfiguration.class] cannot be opened because **it does not exist**

  > 发现xxx not found、not exist，一般都是依赖的问题，版本冲突，jar不完整，依赖未导入等

- java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'string' for key 'uk_name'

  代码里面设置了name属性唯一约束，name赋值重复，抛出以上异常

- ```java
  (Could not set property '" + prop.getName() + "' of '" + object.getClass() + "' with value '" + value + "' Cause: " + var8.toString(), var8);
  ```

  类型不匹配，自动填充修改和增加时间时，使用了`LocalDateTime`，这个属性在3.0.0版本的`springfox`还不支持

  ```java
  this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
  ```

- `org.springframework.beans.factory.UnsatisfiedDependencyException;There is already 'eduTeacherController' bean method`

  restful风格的接口有相同的路径，导致了冲突，修改其中一个即可。

-  oss模块启动报错：`Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.`

   自动装配影响的，springboot默认装配`DataSourceAutoConfiguration`这个类，但是没有在配置文件中配置数据源相关信息，导致找不到数据源报错。

   解决方案：

   ​	1、将`DataSourceAutoConfiguration`这个类排除在自动装配之外，在启动类上加上如下内容即可：

   ​			`@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})`

   ​	2、或者补全数据源信息（这个模块用不上数据源，所以不添加数据源信息了）

# 三、架构

- online-edu（版本仲裁）
  - common（导入依赖）
    - service-base（导入其他模块）
  - service
    - edu-service

# 四、其他

## 1、日志

> OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL

## 2、MP

> - Wrapper
>
>   ge：greater equal
>
>   gt：greater than
>
>   le：lower equal
>
>   lt：lower than
