package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.dabutskikh.questionnaires.model.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByQuestionnaireId(Long questionnaireId);

    @Modifying
    @Query("delete from Question q where q.id=:id")
    void deleteById(@Param("id") Long id);
}
