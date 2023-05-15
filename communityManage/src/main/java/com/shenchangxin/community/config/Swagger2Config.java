package com.shenchangxin.community.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.List;
import static com.google.common.collect.Lists.newArrayList;


@Configuration
@EnableSwagger2
public class Swagger2Config {


    List<String> excludedPathPrefix=null;

    /**
     * 部分路径不需要显示在swagger的ui界面中，在如下函数中进行配置
     */
    public Swagger2Config() {
        excludedPathPrefix=new ArrayList<String>();
        excludedPathPrefix.add("/error");
    }


    public List<String> getExcludedPathPrefix() {
        return excludedPathPrefix;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.regex("^(?!auth).*$"))
                .paths((s)->{
                    for(String pathPrefix:getExcludedPathPrefix()){
                        if(StringUtils.startsWithIgnoreCase(s,pathPrefix)){
                            return false;
                        }
                    }
                    return true;
                })
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<ApiKey> securitySchemes() {
        return newArrayList(
                new ApiKey("Authorization", "Authorization", "header"));
    }

    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder().securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(new SecurityReference("Authorization", authorizationScopes));
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试接口文档")
                .description("Bill Manager System Test")
                .termsOfServiceUrl("https://github.com/Shenchangxin")
                .version("1.0")
                .build();
    }
}

