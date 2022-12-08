package com.mobin.quizappstarter.controllers;

import com.mobin.quizappstarter.entities.Result;
import com.mobin.quizappstarter.models.QuestionForm;
import com.mobin.quizappstarter.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {

    @Autowired private Result result;
    @Autowired private QuestionService questionService;

    @ModelAttribute("result")
    public Result getResult(){
        return result;
    }

    Boolean submitted = false;

    @GetMapping("/")
    public String home(){
        return "index";
    }


    @PostMapping("/quiz")
    public ModelAndView quiz(@RequestParam String name, RedirectAttributes redirectAttributes){
        if(name.isEmpty()){
            redirectAttributes.addFlashAttribute("warning","Name field is required");
            return new ModelAndView("redirect:/");
        }
        submitted = false;
        result.setName(name);
        QuestionForm questionForm = questionService.getQuestions();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("quiz");
        modelAndView.addObject("questionForm",questionForm);
        return modelAndView;
    }


    @PostMapping("/quiz-submit")
    public ModelAndView submit(@ModelAttribute QuestionForm questionForm){
        if(!submitted){
            result.setTotalCorrect(questionService.getResult(questionForm));
            questionService.storeResult(result);
            submitted = true;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");

        return modelAndView;
    }


    @PostMapping("/scores")
    public String scores(){
        return "scoreboard";
    }
}
