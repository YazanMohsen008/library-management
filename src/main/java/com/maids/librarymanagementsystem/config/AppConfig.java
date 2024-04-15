package com.maids.librarymanagementsystem.config;

import com.maids.librarymanagementsystem.config.security.model.CustomUserDetails;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;


@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class AppConfig extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

    @Bean
    @RequestScope
    public CustomUserDetails getCustomUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("cacheManager");
    }

}