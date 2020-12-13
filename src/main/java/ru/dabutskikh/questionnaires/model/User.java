package ru.dabutskikh.questionnaires.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "author",
            orphanRemoval = true
    )
    private List<Questionnaire> questionnaires;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
//    private List<Answer> answers;

    public User() {
    }

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

//    public List<Answer> getAnswers() {
//        return answers;
//    }
//
//    public void setAnswers(List<Answer> answers) {
//        this.answers = answers;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role.name() +
                '}';
    }
}
