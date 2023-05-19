package com.dekard02.librarymanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.github.slugify.Slugify;

@Configuration
@EnableSpringDataWebSupport
@EnableJpaAuditing
public class ApplicationConfig {

    @Bean
    public Slugify slugify() {
        return Slugify.builder().build();
    }
}
