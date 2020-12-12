package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dabutskikh.questionnaires.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
