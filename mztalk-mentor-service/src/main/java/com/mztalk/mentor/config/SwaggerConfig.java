package com.mztalk.mentor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swagger(){
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(java.sql.Date.class)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mztalk.mentor.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(true);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("MZTALK MENTOR SERVICE API DOCUMENT")
                .description("MENTOR SERVICE 상세 소개 및 사용법 등 정보를 제공하기 위한 문서입니다.")
                .contact(new Contact("Jake", "https://velog.io/@cocoding", "bha4388@naver.com"))
                .version("1.0")
                .build();
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }
}
