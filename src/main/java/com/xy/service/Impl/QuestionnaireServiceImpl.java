package com.xy.service.Impl;

import com.xy.mapper.QuestionMapper;
import com.xy.mapper.QuestionnaireMapper;
import com.xy.pojo.Question;
import com.xy.pojo.Questionnaire;
import com.xy.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    private QuestionnaireMapper questionnaireMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public QuestionnaireMapper getQuestionnaireMapper() {
        return questionnaireMapper;
    }

    public void setQuestionnaireMapper(QuestionnaireMapper questionnaireMapper) {
        this.questionnaireMapper = questionnaireMapper;
    }

    public QuestionMapper getQuestionMapper() {
        return questionMapper;
    }

    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    // 将一个问卷，及其列表中的所有问题加入数据库
    public int addFullQuestionnaire(Questionnaire questionnaire) {
        addQuestionnaireWithoutQuestions(questionnaire);
        Integer qnaireID = questionnaire.getQuestionnaireID();  // 获取刚插入的问卷的ID
        List<Question> questions = questionnaire.getQuestions();
        for (Question question : questions) {
            question.setQuestionnaireID(qnaireID);
        }
        questionMapper.addQuestions(questions);


        return qnaireID;
    }

    @Override
    public int addQuestionnaireWithoutQuestions(Questionnaire questionnaire) {
        questionnaireMapper.addQuestionnaire(questionnaire);
        return questionnaire.getQuestionnaireID();
    }

    @Override
    public Questionnaire queryQuestionnaireByID(int questionnaireID) {
        Questionnaire questionnaire = questionnaireMapper.queryQuestionnaireByID(questionnaireID);
        System.out.println(questionnaire);
        return questionnaire;
    }

    @Override
    public Questionnaire queryQuestionnaireBodyByID(int questionnaireID) {
        Questionnaire questionnaire = questionnaireMapper.queryQuestionnaireBodyByID(questionnaireID);
        return questionnaire;
    }

    @Override
    public List<Questionnaire> queryQuestionnaireBodyByUserID(int userID) {
        return questionnaireMapper.queryQuestionnaireBodyByUserID(userID);
    }

    @Override
    public int deleteQuestionnaireByID(int questionnaireID) {
        questionnaireMapper.deleteQuestionnaireByID(questionnaireID);
        return 0;
    }

    @Override
    public int updateQuestionnaire(Questionnaire questionnaire) {
        questionnaireMapper.updateQuestionnaire(questionnaire);
        return 0;
    }
}
