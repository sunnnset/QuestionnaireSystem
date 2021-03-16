package com.xy.service.Impl;

import com.xy.mapper.QuestionMapper;
import com.xy.pojo.Question;
import com.xy.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    public QuestionMapper getQuestionMapper() {
        return questionMapper;
    }

    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public int addQuestion(Question question) {
        questionMapper.addQuestion(question);
        Integer questionID = question.getQuestionID();
        return questionID;
    }

    @Override
    public void addQuestions(List<Question> questions) {
        // TO DO
        questionMapper.addQuestions(questions);
    }

    @Override
    public Question queryQuestionByID(int questionID) {
        return questionMapper.queryQuestionByID(questionID);
    }

    @Override
    public List<Question> queryQuestionsByQnaireID(int questionnaireID) {
        return questionMapper.queryQuestionsByQnaireID(questionnaireID);
    }

    @Override
    public void deleteQuestionByID(int questionID) {
        questionMapper.deleteQuestionByID(questionID);
    }

    @Override
    public void updateQuestion(Question question) {
        questionMapper.updateQuestion(question);
    }
}
