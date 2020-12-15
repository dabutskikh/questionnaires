package ru.dabutskikh.questionnaires.service.interfaces;

import ru.dabutskikh.questionnaires.model.Answer;
import ru.dabutskikh.questionnaires.model.User;

import java.util.Set;

public interface UserService {

    void save(User user);

    void addAnswers(User user, Set<Answer> answers);

    User findByLogin(String login);

    void deleteAnswers(User user);
}
