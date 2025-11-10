package io.github.vahesan1995.helloworld.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {
    @GetMapping("/hello-world")
    public ResponseEntity<Map<String, String>> helloWorld(
            @RequestParam(name = "name", required = false) String name) {

        if (name == null || name.trim().isEmpty()) {
            return badInput();
        }

        String trimmed = name.trim();
        char firstChar = trimmed.charAt(0);

        if (!Character.isLetter(firstChar)) {
            return badInput();
        }

        char upperFirst = Character.toUpperCase(firstChar);
        if (upperFirst >= 'A' && upperFirst <= 'M') {
            String capitalized = capitalize(trimmed);
            Map<String, String> body = new HashMap<>();
            body.put("message", "Hello " + capitalized);
            return ResponseEntity.ok(body);
        } else {
            return badInput();
        }
    }

    private ResponseEntity<Map<String, String>> badInput() {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Invalid Input");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private String capitalize(String name) {
        if (name.isEmpty()) return name;
        if (name.length() == 1) return name.toUpperCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
