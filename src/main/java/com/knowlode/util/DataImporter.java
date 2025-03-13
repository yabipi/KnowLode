package com.knowlode.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowlode.entity.Question;
import com.knowlode.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class DataImporter {

    @Autowired
    private QuestionService questionService;

    public void importDataFromJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = objectMapper.readValue(new File(filePath), Map.class);
        List<Map<String, Object>> questions = (List) data.get("example");
        // 将数据保存到数据库中
        for (Map<String, Object> q : questions) {
            Question question = new Question();
            question.setTitle((String) q.get("question"));
            question.setContent((String) q.get("analysis"));
            question.setAnswer(String.join(",", (List<String>) q.get("answer")));
            //question.setCategoryId(1L); // 假设有一个默认的分类ID

            question.setStatus(0);
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setFavoriteCount(0);

            questionService.saveQuestion(question);
        }
    }
}