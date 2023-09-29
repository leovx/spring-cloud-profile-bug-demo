package com.jinghao.demo.autoconfigure;

import com.jinghao.demo.auth.AuthManager;
import com.jinghao.demo.auth.LocalBypassAuthManager;
import com.jinghao.demo.auth.NormalAuthManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Assume this is from some other package that configures some beans for different environment,
 * the {@link LocalBypassAuthManager} should only be used for local dev test purpose,
 * however, due to active profile issue, the bean might be activated by mistake.
 */
@Configuration
@Slf4j
public class ApplicationAutoConfiguration {

    @Bean
    @Profile("local")
    public AuthManager localBypassAuthManager() {
        log.info("Oops we introduced a bean intended for local!");
        return new LocalBypassAuthManager();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthManager remoteAuthManager() {
        log.info("PROD env should use this bean");
        return new NormalAuthManager();
    }

}
