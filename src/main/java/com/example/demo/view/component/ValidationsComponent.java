package com.example.demo.view.component;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidationsComponent {

    // private final MessageComponent message;

    public void add(BindingResult result, boolean notValid, String code, Object... args) {
        if (notValid) {
            result.reject(null, args, code);
        }
    }

}
