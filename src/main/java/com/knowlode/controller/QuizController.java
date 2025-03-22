package com.knowlode.controller;

import com.knowlode.entity.Question;
import com.knowlode.entity.Quiz;
import com.knowlode.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping(value = "/list")
    public PagedModel<Quiz> getAllQuizzes(@RequestParam(value="pageNo") int pageNo, @RequestParam(value="pageSize") int pageSize) {
        return new PagedModel(quizService.getQuizs(pageNo, pageSize));
    }

    @GetMapping(value = "/listquestions")
    public List<Question> getQuestionsOfQuiz(@RequestParam(value="quizId") Long quizId) {
        return quizService.getQuestionsByQuiz(quizId);
    }

    @PostMapping
    public String createQuiz(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
        //return questionService.saveQuestion(question);
        quizService.addQuiz(title, content);
        return "success";
    }


}
