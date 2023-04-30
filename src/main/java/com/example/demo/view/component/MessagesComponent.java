package com.example.demo.view.component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.ServiceException;
import com.example.demo.view.exception.MessageException;
import com.example.demo.view.model.Message;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessagesComponent {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String WARNING = "warning";
    public static final String INFO = "info";

    private final MessageSource messageSource;

    // -- Model

    public void success(Model model, String code, Object... args) {
        this.message(model, SUCCESS, code, args);
    }

    public void error(Model model, String code, Object... args) {
        this.message(model, ERROR, code, args);
    }

    public void error(Model model, BindingResult result) {
        result.getAllErrors().stream().forEach(e -> this.message(model, this.error(e)));
    }

    public void error(Model model, ServiceException e) {
        this.error(model, e.getMessage(), e.getArgs());
    }

    public void warning(Model model, String code, Object... args) {
        this.message(model, WARNING, code, args);
    }

    public void info(Model model, String code, Object... args) {
        this.message(model, INFO, code, args);
    }

    public void message(Model model, String type, String code, Object... args) {
        var message = this.message(type, code, args);
        this.message(model, message);
    }

    public void message(Model model, Message message) {
        if (model instanceof RedirectAttributes) {
            var redirect = (RedirectAttributes) model;
            this.addOnMap(message, redirect::getFlashAttributes, redirect::addFlashAttribute);
        } else {
            this.addOnMap(message, model::asMap, model::addAttribute);
        }
    }

    @SuppressWarnings("unchecked")
    private void addOnMap(Message message, Supplier<Map<String, ?>> asMap,
            BiFunction<String, Set<Message>, ?> add) {
        var attrName = message.getType() + "List";
        var messages = new HashSet<Message>();
        var map = asMap.get();
        if (map.containsKey(attrName)) {
            messages.addAll((Set<Message>) map.get(attrName));
        }
        messages.add(message);
        add.apply(attrName, messages);
    }

    // -- Returns Message

    public Message success(String code, Object... args) {
        return this.message(SUCCESS, code, args);
    }

    public Message error(String code, Object... args) {
        return this.message(ERROR, code, args);
    }

    public Message error(ObjectError error) {
        return this.error(error.getDefaultMessage(), error.getArguments());
    }

    public Message warning(String code, Object... args) {
        return this.message(WARNING, code, args);
    }

    public Message info(String code, Object... args) {
        return this.message(INFO, code, args);
    }

    public Message message(String type, String code, Object... args) {
        return new Message(type, i18n(code, args));
    }

    // -- Returns Exception

    public MessageException error(ServiceException e) {
        return new MessageException(e, this.error(e.getMessage(), e.getArgs()));
    }

    public MessageException error(BindingResult result) {
        return new MessageException(result.getAllErrors().stream().map(this::error).toArray(Message[]::new));
    }

    // -- Source

    public String i18n(String code, Object... args) {
        return messageSource.getMessage(code, args, code, null);
    }

}
