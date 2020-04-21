package cz.cvut.fel.rsp.travelandwork.environment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@ComponentScan(basePackages = "cz.cvut.fel.rsp.travelandwork.security")
public class TestSecurityConfig {
}
