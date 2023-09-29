package com.jinghao.demo.autoconfigure;

import com.jinghao.demo.locator.SecurityPropertySourceLocator;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Bean;

/**
 * Assume this auto-config is from some other packages for securely fetching data from some vault
 */
@AutoConfiguration
public class BootstrapAutoConfiguration {

    @Bean
    public PropertySourceLocator securityPropertySourceLocator() {
        return new SecurityPropertySourceLocator();
    }

}
