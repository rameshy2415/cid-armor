package com.hackathon.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tokenizer")
public class TokenizerController {

    @Autowired
    private TokenizerService tokenizerService;

    @PostMapping("/tokenize")
    public ResponseEntity<Person> tokenize(@RequestBody String xml) throws Exception {
        Custome tokenized = tokenizerService.processXml(xml);
        return ResponseEntity.ok(tokenized);
    }

    @GetMapping("/detokenize")
    public ResponseEntity<String> detokenize(@RequestParam String token) {
        return ResponseEntity.ok(tokenizerService.detokenize(token));
    }
}

