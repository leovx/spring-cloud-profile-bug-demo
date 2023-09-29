package com.jinghao.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class ActiveProfileLogListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {
    public static final int ORDER = Ordered.HIGHEST_PRECEDENCE + 100;

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment env = event.getEnvironment();
        log.info("Active from env method: "+ String.join(";", env.getActiveProfiles()));
        env.getPropertySources().stream().forEach(e -> log.info(e.getName()+": "+e.getProperty("spring.profiles.active")));
    }

    @Override
    public int getOrder() {
        return ORDER;
    }

}
