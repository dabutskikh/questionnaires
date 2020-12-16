package ru.dabutskikh.questionnaires.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "client_answer")
public class UserAnswer {
    @Embeddable
    public static class UserAnswerId implements Serializable {
        @ManyToOne
        @JoinColumn(name = "client_id")
        private User user;

        @ManyToOne
        @JoinColumn(name = "answer_id")
        private Answer answer;

        public UserAnswerId() {
        }

        public UserAnswerId(User user, Answer answer) {
            this.user = user;
            this.answer = answer;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Answer getAnswer() {
            return answer;
        }

        public void setAnswer(Answer answer) {
            this.answer = answer;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserAnswerId that = (UserAnswerId) o;
            return Objects.equals(user, that.user) &&
                    Objects.equals(answer, that.answer);
        }

        @Override
        public int hashCode() {
            return Objects.hash(user, answer);
        }
    }

    @EmbeddedId
    private UserAnswerId userAnswerId;


    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private UserAnswerStatus status;

    public UserAnswer() {
    }

    public UserAnswer(UserAnswerId userAnswerId, UserAnswerStatus status) {
        this.userAnswerId = userAnswerId;
        this.status = status;
    }

    public UserAnswerId getUserAnswerId() {
        return userAnswerId;
    }

    public void setUserAnswerId(UserAnswerId userAnswerId) {
        this.userAnswerId = userAnswerId;
    }

    public UserAnswerStatus getStatus() {
        return status;
    }

    public void setStatus(UserAnswerStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAnswer that = (UserAnswer) o;
        return Objects.equals(userAnswerId, that.userAnswerId) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAnswerId, status);
    }

    @Override
    public String toString() {
        return "UserAnswer{" +
                "userId=" + userAnswerId.getUser() +
                ", answerId=" + userAnswerId.getAnswer() +
                ", status=" + status +
                '}';
    }
}
