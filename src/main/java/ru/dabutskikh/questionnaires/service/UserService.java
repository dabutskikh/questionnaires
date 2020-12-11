package ru.dabutskikh.questionnaires.service;

import ru.dabutskikh.questionnaires.model.User;

public interface UserService {

    void save(User user);

    User findByLogin(String login);
}
