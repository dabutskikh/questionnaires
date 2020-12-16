package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.dabutskikh.questionnaires.model.UserAnswer;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, UserAnswer.UserAnswerId> {

    @Query("select userAnswer from UserAnswer userAnswer where " +
            "userAnswer.userAnswerId.user.id=:userId" +
            " and " +
            "userAnswer.userAnswerId.answer.id=:answerId"
    )
    List<UserAnswer> findByUserIdAndAnswerId(Long userId, Long answerId);
}
