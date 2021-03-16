package com.xy.service.Impl;

import com.xy.mapper.QuestionAnswerMapper;
import com.xy.pojo.QuestionAnswer;
import com.xy.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {
    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;

    @Override
    public int addQA(QuestionAnswer questionAnswer) {
        int id = questionAnswerMapper.addQA(questionAnswer);
        return id;
    }

    @Override
    public int addQAs(List<QuestionAnswer> list) {
        questionAnswerMapper.addQAs(list);
        return 0;
    }

    @Override
    public int deleteQA(int ID) {
        questionAnswerMapper.deleteQA(ID);
        return 0;
    }

    @Override
    public int updateQA(QuestionAnswer questionAnswer) {
        questionAnswerMapper.updateQA(questionAnswer);
        return 0;
    }

    @Override
    public QuestionAnswer getQAByID(int ID) {
        return questionAnswerMapper.getQAByID(ID);
    }

    @Override
    public List<QuestionAnswer> getQAsByQuestionnaireAnswerID(int questionnaireAnswerID) {
        return questionAnswerMapper.getQAsByQuestionnaireAnswerID(questionnaireAnswerID);
    }
}
