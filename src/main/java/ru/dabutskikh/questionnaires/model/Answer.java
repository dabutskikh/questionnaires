package ru.dabutskikh.questionnaires.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToMany(mappedBy = "answers")
    private Set<User> users;

    public Answer() {
    }

    public Answer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public boolean isChangeable() {
        return question.isChangeable();
    }

//    @Override
//    public String toString() {
//        return "Answer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", question=" + question +
//                '}';
//    }
}
