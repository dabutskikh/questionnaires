package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dabutskikh.questionnaires.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
