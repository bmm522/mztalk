package com.mztalk.resource.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfig{


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
                        .basePackage("com.mztalk.resource.controller"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("MZTALK-RESOURCE")
                .description("Mztalk Resource server api document")
                .build();
    }

    private Set<String> getConsumeContentTypes(){
        Set<String> consumes = new HashSet<>();
        consumes.add("text/html");
        consumes.add("application/json;charset=UTF-8");
        consumes.add("multipart/form-data");
        return consumes;
    }

    private Set<String> getProduceContentTypes(){
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        produces.add("text/html");
        produces.add("multipart/form-data");
        return produces;
    }




}
