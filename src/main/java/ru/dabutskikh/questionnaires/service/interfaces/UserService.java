package ru.dabutskikh.questionnaires.service.interfaces;

import ru.dabutskikh.questionnaires.model.*;

import java.util.Set;

public interface UserService {

    void save(User user);

    User findByLogin(String login);

    User findById(Long id);
}
