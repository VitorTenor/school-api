package com.school.core.util;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket swagger() {
        return new Docket(
                DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.myapi.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

private ApiInfo metaData() {
     ApiInfo apiInfo = new ApiInfo (
             "School Api",
             "API rest de registro de usuários e alunos",
             "1.0",
             "Terms of Service",
             new Contact("Vitor Tenorio",
                     "https://master.tenoriodev.com.br",
                     "vitortenorio.dev@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html",
             new ArrayList<VendorExtension>()
     );
     return apiInfo;
    }
}
