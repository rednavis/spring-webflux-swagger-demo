package com.rednavis.webflux.demo.config;

import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.PathMatchConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {

  @NotNull
  @Value("${spring.webflux.base-path}")
  private String basePath;

  @Override
  public void configurePathMatching(PathMatchConfigurer configurer) {
    configurer
        .setUseCaseSensitiveMatch(true)
        .setUseTrailingSlashMatch(false)
        .addPathPrefix(basePath,
            HandlerTypePredicate.forAnnotation(RestController.class));
  }
}
