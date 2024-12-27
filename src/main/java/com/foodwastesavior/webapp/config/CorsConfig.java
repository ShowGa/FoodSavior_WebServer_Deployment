package com.foodwastesavior.webapp.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    private static final String FRONTEND_URL_USER = Dotenv.load().get("FRONTEND_URL_USER");

    private static final String FRONTEND_URL_MYSTORE = Dotenv.load().get("FRONTEND_URL_MYSTORE");

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin(FRONTEND_URL_MYSTORE);
        config.addAllowedOrigin(FRONTEND_URL_USER);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 应用于所有路径
        return new CorsFilter(source);
    }
}

