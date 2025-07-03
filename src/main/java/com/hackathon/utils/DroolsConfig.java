package com.hackathon.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {
    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write(ResourceFactory.newClassPathResource("rules/token-rules.drl"));
        KieBuilder kb = ks.newKieBuilder(kfs).buildAll();
        return ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
    }
}
