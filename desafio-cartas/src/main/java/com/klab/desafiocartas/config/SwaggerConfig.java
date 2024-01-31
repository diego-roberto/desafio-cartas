package com.klab.desafiocartas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableOpenApi
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return (new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.klab.desafiocartas.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()));
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("desafio-cartas-api")
                .description("Desafios Cartas API")
                .version("0.1.0")
                .contact(contact())
                .build();
    }

    private Contact contact(){
        return new Contact(
                "Diego Roberto",
                "https://github.com/diego-roberto/",
                "diego-roberto@hotmail.com");
    }
}
