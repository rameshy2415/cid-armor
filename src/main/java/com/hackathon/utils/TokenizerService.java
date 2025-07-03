package com.hackathon.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenizerService {
    @Autowired
    private RuleEngineService ruleEngineService;

    public Person processXml(String xml) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Person person = (Person) unmarshaller.unmarshal(new StringReader(xml));

        ruleEngineService.applyRules(person);

        for (String field : person.getFieldsToTokenize()) {
            switch (field) {
                case "name":
                    person.setName(TokenUtils.tokenize(person.getName(), 10, "TXT"));
                    break;
                case "bankAccount":
                    person.setBankAccount(TokenUtils.tokenize(person.getBankAccount(), 12, "NUM"));
                    break;
            }
        }
        return person;
    }

    public String detokenize(String token) {
        return TokenUtils.detokenize(token);
    }
}

