package com.mobin.quizappstarter.services;

import com.mobin.quizappstarter.entities.Question;
import com.mobin.quizappstarter.entities.Result;
import com.mobin.quizappstarter.models.QuestionForm;
import com.mobin.quizappstarter.repositories.QuestionRepository;
import com.mobin.quizappstarter.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionService {

    @Autowired Question question;
    @Autowired QuestionForm questionForm;
    @Autowired QuestionRepository questionRepo;
    @Autowired Result result;
    @Autowired ResultRepository resultRepository;


    public QuestionForm getQuestions(){
        List<Question> allQuestion = questionRepo.findAll();
        List<Question> questionList = new ArrayList<>();

        Random random = new Random();
        for(int i=0; i<5; i++){
            int rand = random.nextInt(allQuestion.size());
            questionList.add(allQuestion.get(rand));
            allQuestion.remove(rand);
        }

        questionForm.setQuestions(questionList);
        return questionForm;
    }


    public int getResult(QuestionForm questionForm){
        int correct = 0;
        for(Question que : questionForm.getQuestions()){
            if(que.getCorrect() == que.getChose()){
                correct++;
            }
        }

        return correct;
    }


    public void storeResult(Result result){
        Result saveResult = new Result();
        saveResult.setName(result.getName());
        saveResult.setTotalCorrect(result.getTotalCorrect());
        resultRepository.save(saveResult);
    }

    public List<Result> getResults(){
        return resultRepository.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
    }
}
