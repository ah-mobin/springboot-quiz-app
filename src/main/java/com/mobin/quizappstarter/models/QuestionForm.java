package com.mobin.quizappstarter.models;

import com.mobin.quizappstarter.entities.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionForm {

    private List<Question> questions;

    public List<Question> getQuestions(){
        return questions;
    }


    public void setQuestions(List<Question> questions){
        this.questions = questions;
    }
}
