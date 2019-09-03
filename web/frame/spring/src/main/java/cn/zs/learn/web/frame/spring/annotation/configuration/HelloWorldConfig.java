package cn.zs.learn.web.frame.spring.annotation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {
    @Bean
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }
}
