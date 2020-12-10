package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dabutskikh.questionnaires.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);
}
