package com.knowlode.service;

import com.knowlode.entity.Question;
import com.knowlode.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz addQuiz(String title, String content);

    public List<Question> getQuestionsByQuiz(Long quizId);

    public Page<Quiz> getQuizs(int pageNo, int pageSize);

}
