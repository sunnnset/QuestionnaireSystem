package com.xy.service;

import com.xy.pojo.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {

    int addQA(QuestionAnswer questionAnswer);

    int addQAs(List<QuestionAnswer> list);

    int deleteQA(int ID);

    int updateQA(QuestionAnswer questionAnswer);

    QuestionAnswer getQAByID(int ID);

    List<QuestionAnswer> getQAsByQuestionnaireAnswerID(int questionnaireAnswerID);
}
