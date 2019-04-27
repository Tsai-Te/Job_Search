package com.te.config;

import com.te.config.viewresolver.JsonViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("com.te.api") //todo ask
public class MvcConfig implements WebMvcConfigurer { // revoke from servlet. servlet has all the api. servlet -- server//scan frontend, servlet

    @Override
    public void configureContentNegotiation (ContentNegotiationConfigurer configurer) { //todo ask
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver (ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver=new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        List<ViewResolver> resolvers=new ArrayList<ViewResolver>();
        resolvers.add(jsonViewResolver());

        resolver.setViewResolvers(resolvers);
        return resolver;
    }

    public ViewResolver jsonViewResolver(){
        return new JsonViewResolver();
    }
}
