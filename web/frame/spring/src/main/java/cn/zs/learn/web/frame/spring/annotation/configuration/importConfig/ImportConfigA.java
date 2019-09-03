package cn.zs.learn.web.frame.spring.annotation.configuration.importConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
@Import(ConfigA.class)
@Configuration
public class ImportConfigA {
    public B b() {
        return new A();
    }
}
class B{}