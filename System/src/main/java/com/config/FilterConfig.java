package com.config;

import com.filter.CorsFilter;
import com.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter();
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterFilterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(jwtFilter());
        bean.setName("Jwt过滤器");
        bean.addUrlPatterns("/*");
        bean.setOrder(2);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new CorsFilter());
        bean.setName("跨域过滤器");
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }
}
