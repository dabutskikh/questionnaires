package ru.dabutskikh.questionnaires.service.interfaces;

import ru.dabutskikh.questionnaires.model.User;

import java.util.Optional;

public interface UserService {

    void save(User user);

    User findByLogin(String login);
}
