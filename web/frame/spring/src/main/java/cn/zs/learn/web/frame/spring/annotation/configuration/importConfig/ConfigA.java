package cn.zs.learn.web.frame.spring.annotation.configuration.importConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigA {
    @Bean
    public A a() {
        return new A();
    }
}

class A extends B{}