package com.upgrad.quora.service.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;


/**
 * AnswerEntity class contains all the attributes to be mapped to all the fields in ANSWER table in the database.
 * All the annotations which are used to specify all the constraints to the columns in the database must be correctly implemented.
 */
@NamedQueries({
        @NamedQuery(name = "answerByUuid", query = "select a from AnswerEntity a where a.uuid= :uuid"),
        @NamedQuery(name = "getAllAnswersByQuestion", query = "select a from AnswerEntity a where a.question = :question")
})
@Entity
@Table(name = "ANSWER")
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UUID")
    @Size(max = 64)
    @NotNull
    private String uuid;


    @Column
    @NotNull
    private String ans;

    @Column
    @NotNull
    private ZonedDateTime date;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private UserEntity user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "QUESTION_ID")
    @NotNull
    private QuestionEntity question;

    public AnswerEntity() {
    }

    public AnswerEntity(String ans, ZonedDateTime date, UserEntity user, QuestionEntity question) {
        this.ans = ans;
        this.date = date;
        this.user = user;
        this.question = question;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }
}

