package ru.dabutskikh.questionnaires.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.dabutskikh.questionnaires.service.interfaces.AnswerService;
import ru.dabutskikh.questionnaires.service.interfaces.UserAnswerService;
import ru.dabutskikh.questionnaires.service.interfaces.UserService;


@Component
public class UserAnswerConverter implements Converter<String, UserAnswer> {

    @Autowired
    UserService userService;

    @Autowired
    AnswerService answerService;

    @Override
    public UserAnswer convert(String s) {

        String[] values = s.split("_");
        Long userId = Long.parseLong(values[0]);
        Long answerId = Long.parseLong(values[1]);

        return new UserAnswer(
                new UserAnswer.UserAnswerId(userService.findById(userId), answerService.findById(answerId)),
                UserAnswerStatus.TEMPORARY
        );
    }
}
