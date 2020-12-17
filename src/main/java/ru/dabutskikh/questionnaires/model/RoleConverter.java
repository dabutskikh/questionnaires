package ru.dabutskikh.questionnaires.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String s) {
        return Role.valueOf(s);
    }
}
