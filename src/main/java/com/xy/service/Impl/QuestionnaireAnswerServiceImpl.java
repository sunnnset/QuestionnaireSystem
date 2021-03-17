package com.xy.service.Impl;

import com.xy.mapper.QuestionAnswerMapper;
import com.xy.mapper.QuestionnaireAnswerMapper;
import com.xy.pojo.QuestionAnswer;
import com.xy.pojo.QuestionnaireAnswer;
import com.xy.service.QuestionnaireAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireAnswerServiceImpl implements QuestionnaireAnswerService {

    @Autowired
    private QuestionnaireAnswerMapper qaAnswerMapper;
    @Autowired
    private QuestionAnswerMapper answerMapper;

    @Override
    // 添加问卷回答和所有的问题回答
    public int addFullQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer) {
        // 返回添加问卷回答的id
        // 先添加问卷回答
        System.out.println("添加的问卷回答：");
        System.out.println(questionnaireAnswer);
        List<QuestionAnswer> list = questionnaireAnswer.getAnswers();
        for (QuestionAnswer questionAnswer : list) {
            System.out.println(questionAnswer);
        }
        qaAnswerMapper.addQaAnswerBody(questionnaireAnswer);
        int id = questionnaireAnswer.getAnswerID();
        // 再将问题回答的问卷回答id设为刚返回的id，并添加所有问题回答
        List<QuestionAnswer> answers = questionnaireAnswer.getAnswers();
        for (QuestionAnswer answer : answers) {
            answer.setQuestionnaireAnswerID(id);
        }
        answerMapper.addQAs(answers);
        return id;
    }

    @Override
    public int addQuestionnaireAnswerWithoutQAnswer(QuestionnaireAnswer questionnaireAnswer) {
        // 返回添加问卷回答的id
        return qaAnswerMapper.addQaAnswerBody(questionnaireAnswer);
    }

    @Override
    public QuestionnaireAnswer getQuestionnaireAnswerByID(int QaAnswerID) {
        return qaAnswerMapper.getQaAnswerByID(QaAnswerID);
    }

    @Override
    public QuestionnaireAnswer getQuestionnaireAnswerBodyByID(int QaAnswerID) {
        return qaAnswerMapper.getQaAnswerBodyByID(QaAnswerID);
    }

    @Override
    public List<QuestionnaireAnswer> getQuestionnaireAnswerBodiesByUserID(int userID) {
        return qaAnswerMapper.getQaAnswerBodiesByUserID(userID);
    }

    @Override
    public int deleteQuestionnaireAnswerByID(int QaAnswerID) {
        qaAnswerMapper.deleteQaAnswer(QaAnswerID);
        return 0;
    }

    @Override
    public int updateQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer) {
        qaAnswerMapper.updateQaAnswerBody(questionnaireAnswer);
        return 0;
    }
}
