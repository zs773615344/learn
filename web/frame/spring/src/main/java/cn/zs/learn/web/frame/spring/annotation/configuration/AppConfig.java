package cn.zs.learn.web.frame.spring.annotation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean(initMethod="init",destroyMethod="destory")
    @Scope("prototype")
    public Foo foo() {
        return new Foo();
    }
}

class Foo{
    public void init() {}
    public void destory() {}
}