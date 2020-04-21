package cz.cvut.fel.rsp.travelandwork.environment.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.env.MockEnvironment;

public class PropertyMockingApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        MockEnvironment mockEnvironment = new MockEnvironment();
        configurableApplicationContext.setEnvironment(mockEnvironment);
    }

}
