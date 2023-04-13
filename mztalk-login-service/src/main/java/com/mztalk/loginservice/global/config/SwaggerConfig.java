package com.mztalk.loginservice.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket commonApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();

        parameterBuilder
                .name("Content-Type")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("example")
                .globalOperationParameters(parameters)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .useDefaultResponseMessages(false)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.mztalk.login.controller"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("MZTALK-LOGIN")
                .description("Mztalk Login service api document")
                .build();
    }

    private Set<String> getConsumeContentTypes(){
        Set<String> consumes = new HashSet<>();
        consumes.add("text/html");
        consumes.add("application/json;charset=UTF-8");
        return consumes;
    }

    private Set<String> getProduceContentTypes(){
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        produces.add("text/html");
        return produces;
    }
}
