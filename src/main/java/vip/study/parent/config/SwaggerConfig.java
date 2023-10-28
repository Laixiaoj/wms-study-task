package vip.study.parent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // 配置类
@EnableSwagger2 // 开启 swagger2 的自动配置
@EnableKnife4j
public class SwaggerConfig implements WebMvcConfigurer {

    /*
     * @配置docket以配置Swagger具体参数
     * */
    @Bean
    public Docket createRestApi1(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("jet.lai")
                .select()
                .apis(RequestHandlerSelectors.basePackage("vip.study.parent.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("vincent.lai")//配置Swagger的信息=apiInfo
                .select()
                .apis(RequestHandlerSelectors.basePackage("vip.study.parent.controller"))  // 配置扫描接口，RequestHandlerSelectors.any()默认全部
                .paths(PathSelectors.any()) // 过滤扫描接口，PathSelectors.any()默认不进行过滤
                .build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("在线API文档")
                .description("聚合API在线测试")
                .version("1.0")
                .build();
    }

}
