package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dabutskikh.questionnaires.model.Questionnaire;

import java.util.List;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    List<Questionnaire> findAll();
}
