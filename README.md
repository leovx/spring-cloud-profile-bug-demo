# spring-cloud-profile-bug-demo

## Background
This project is to demonstrate the bug introduced since Spring Cloud Common 3.1.7, which breaches below rules, while versions before 3.1.6 does not have such issue. I can see the issue was accidentally introduced from this [PR](https://github.com/spring-cloud/spring-cloud-commons/pull/1228/files) by merging all values under *spring.profiles.active*.

According to [3.1. Adding Active Profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.profiles.adding-active-profiles):

> "The ***spring.profiles.active*** property follows the same ordering rules as other properties: The highest ***PropertySource*** wins. This means that you can specify active profiles in ***application.properties*** and then ***replace*** them by using the command line switch."

## Trigger Conditions:
0. Using Spring Cloud 2021.0.8 (Spring Cloud Common 3.1.7) onwards
1. Using Spring Boot 2.7.x onwards
2. In application.yml, we give (for out-of-box experience, run locally for dev and test once after clone)
> spring.profiles.active: local
3. In command line or any other remote property source which has higher priority, e.g. Kubernetes ConfigMap, we give
> spring.profiles.active: prod, secure
> #intended to override and replace value in application.yml
4. Provide a bean that implements *org.springframework.cloud.bootstrap.config.PropertySourceLocator*

## Buggy Behaviour
Instead of accepting "*prod*" and "*secure*" profiles only, it accepts *local* profile as well.
> INFO 5445 --- [           main] com.jinghao.demo.DemoApplication         : The following 3 profiles are active: "prod", "secure", "local"

## Potential Hazard
In this demostration project, 2 beans of AuthManager would be candidates to use - LocalBypassAuthManager should only be used in local development environment due to some reasons (say something cannot be installed locally but should not impact your BAU functional implementation), while NormalAuthManager should be applied in any valid remote environment - especially Production. In this case, both "prod" and "local" profiles are activated and hence the illegal bean of LocalBypassAuthManager will be used in Production making any authentication tokens bypass and become valid.