package com.hackathon.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleEngineService {
    @Autowired
    private KieContainer kieContainer;

    public void applyRules(Person person) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(person);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
