package com.eriel.amex.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    private String TITLE = "Eriel Amex Demo REST Api";
    private String DESCRIPTION = "A Restful api to retrieve user information. Uses MongoDB set up in a Google " +
            "Cloud Virtual Machine.";
    private String VERSION = "0.1";
    private String TERMS_OF_SERVICE_URL = "http://www.example.com";
    private Contact DEV_CONTACT = new Contact("Eriel Marimon", "http://www.example.com", "erieljr1@gmail.com");
    private String LICENCE_NAME = "Some Licence, for example Apache Licence Version 2.0";
    private String LICENCE_URL = "https://www.apache.org/licenses/LICENSE-2.0";


    /**
     * Configures Swagger and makes the UI availale at /swagger-ui.html
     * @return The Docket object used by springmvc to build the swagger ui.
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Build the metadata for the Api to be shown in the Swagger-UI page.
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfo(
                TITLE,
                DESCRIPTION,
                VERSION,
                TERMS_OF_SERVICE_URL,
                DEV_CONTACT,
                LICENCE_NAME,
                LICENCE_URL);
    }

}
