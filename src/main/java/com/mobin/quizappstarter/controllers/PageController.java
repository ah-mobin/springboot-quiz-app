package com.mobin.quizappstarter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {

    @GetMapping("/")
    public String home(){
        return "index";
    }


    @PostMapping("/quiz")
    public ModelAndView quiz(@RequestParam String name, Model model, RedirectAttributes redirectAttributes){
        if(name.isEmpty()){
            redirectAttributes.addFlashAttribute("warning","Name field is required");
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("redirect:/");
    }

}
