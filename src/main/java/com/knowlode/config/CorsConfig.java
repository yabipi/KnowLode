package com.knowlode.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer {
    static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE", "OPTIONS" };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /**
         * org.springframework.web.util.NestedServletException: Request processing failed; nested exception
         * is java.lang.IllegalArgumentException:
         * When allowCredentials is true, allowedOrigins cannot contain the special value "*" since that cannot be set on
         * the "Access-Control-Allow-Origin" response header.
         * To allow credentials to a set of origins, list them explicitly or consider using "allowedOriginPatterns" instead.
         */
        //registry.addMapping("/**").allowedOriginPatterns("*").allowCredentials(true).allowedMethods(ORIGINS).maxAge(3600);
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // SpringBoot2.4.0 [allowedOriginPatterns]代替[allowedOrigins]
                .allowedMethods("*")
                .maxAge(3600)
                .allowCredentials(true);
                //.allowCredentials(false); // allowCredentials设为true会与WebSocket跨域冲突

    }
}
