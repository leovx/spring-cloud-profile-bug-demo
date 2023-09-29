package com.jinghao.demo.locator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SecurityPropertySourceLocator implements PropertySourceLocator {
    @Override
    public PropertySource<?> locate(Environment environment) {
        log.info("Get some properties securely from some vault");
        Map<String, Object> properties = new HashMap<>();
        return new MapPropertySource("securityPropertiesMapping", properties);
    }
}
