package com.knowlode.service.impl;

import com.knowlode.entity.Quiz;
import com.knowlode.entity.Question;
import com.knowlode.entity.QuizQuestion;
import com.knowlode.repository.QuestionRepository;
import com.knowlode.repository.QuizRepository;
import com.knowlode.repository.QuizQuestionRepository;
import com.knowlode.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("QuizService")
@Transactional
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz addQuiz(String title, String content) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz = quizRepository.save(quiz);

        List<Question> questions = extractQuestions(content);
        try {
            for (Question question : questions) {
                //question.setQuiz(quiz);
                Question ques = questionRepository.save(question);
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setQuizId(quiz.getQuizId());
                quizQuestion.setQuestionId(ques.getQuestionId());
                quizQuestionRepository.save(quizQuestion);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return quiz;
    }

    private List<Question> extractQuestions(String content) {
        List<Question> questions = new ArrayList<>();
        String[] lines = content.split("\n");
        Question currentQuestion = null;
        StringBuilder currentTitle = new StringBuilder();
        StringBuilder currentContent = new StringBuilder();
        boolean isInTitle = false;
        boolean isInContent = false;

        for (String line : lines) {
            if (line.length() == 0) continue;
            if (line.matches("^\\d+\\..*")) {
                isInTitle = true;
                isInContent = false;
                currentTitle.setLength(0);
                if (currentQuestion != null) {
                    currentQuestion.setContent(currentContent.toString().trim());
                    questions.add(currentQuestion);
                    currentContent.setLength(0);
                }
                currentQuestion = new Question();
                currentTitle.append(line.substring(line.indexOf('.') + 1).trim());
                //currentQuestion.setTitle(line.substring(line.indexOf('.') + 1).trim());
                //currentContent = new StringBuilder();
            } else if (line.startsWith("#####")) {
                isInTitle = false;
                isInContent = true;
                currentQuestion.setTitle(currentTitle.toString());
                currentTitle.setLength(0);
                currentContent.append(line.substring(5).trim());
            } else if (currentQuestion != null) {
                if (isInTitle) {
                    currentTitle.append("\n").append(line);
                }
                if (isInContent) {
                    currentContent.append("\n").append(line);
                }
                //if (currentContent.length() > 0) {
                //    currentContent.append("\n");
                //}
                //currentContent.append(line);
            }
        }

        if (currentQuestion != null) {
            currentQuestion.setContent(currentContent.toString().trim());
            questions.add(currentQuestion);
        }

        return questions;
    }

    public Page<Quiz> getQuizs(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return quizRepository.findAll(pageable);
    }

    @Override
    public List<Question> getQuestionsByQuiz(Long quizId) {
        try {
            List<QuizQuestion> quizQuestions = quizQuestionRepository.findQuizQuestionsByQuizId(quizId.intValue());
            List<Question> questions = new ArrayList<>();
            for (QuizQuestion quizQuestion : quizQuestions) {
                Question question = questionRepository.findById(quizQuestion.getQuestionId()).orElse(null);
                if (question != null) {
                    questions.add(question);
                }
            }
            return questions;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //return List.of();
    }
}