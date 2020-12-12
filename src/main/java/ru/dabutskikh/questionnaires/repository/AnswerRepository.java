package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dabutskikh.questionnaires.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
