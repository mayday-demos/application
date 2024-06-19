package org.mayday.application.controller;

import org.mayday.application.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private MyConfig myConfig;

    @GetMapping("/")
    public String getDynamicString() {
        return myConfig.getDynamicString();
    }
}
