package org.mayday.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyConfig {

    @Value("${myapp.dynamic-string}")
    private String dynamicString;

    public String getDynamicString() {
        return dynamicString;
    }
}