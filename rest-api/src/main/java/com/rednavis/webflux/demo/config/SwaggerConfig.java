package com.rednavis.webflux.demo.config;

import java.util.Collections;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfig {

  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .apiInfo(getApiInfo())
        .genericModelSubstitutes(Optional.class);
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo(
        "REST API documentation",
        "TBD",
        "1.0",
        "TERMS OF SERVICE URL",
        new Contact("NAME", "URL", "EMAIL"),
        "LICENSE",
        "LICENSE URL",
        Collections.emptyList());
  }
}