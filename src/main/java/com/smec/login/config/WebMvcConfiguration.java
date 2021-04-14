package com.smec.login.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {
    private ObjectMapper mapper;

    public WebMvcConfiguration(ObjectMapper mapper) {
        // default mapper configured with spring.*
        this.mapper = mapper;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer ) {
        configurer.defaultContentType( MediaType.APPLICATION_JSON );
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> httpConverter : converters) {
            if (httpConverter instanceof MappingJackson2HttpMessageConverter) {
                // register the configured object mapper to HttpMessageconter
                ((MappingJackson2HttpMessageConverter) httpConverter).setObjectMapper(mapper);
            }
        }
    }
}
