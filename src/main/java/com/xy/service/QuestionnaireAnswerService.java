package com.xy.service;

import com.xy.pojo.QuestionAnswer;
import com.xy.pojo.QuestionnaireAnswer;

import java.util.List;

public interface QuestionnaireAnswerService {

    int addFullQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer);

    int addQuestionnaireAnswerWithoutQAnswer(QuestionnaireAnswer questionnaireAnswer);

    QuestionnaireAnswer getQuestionnaireAnswerByID(int QaAnswerID);

    QuestionnaireAnswer getQuestionnaireAnswerBodyByID(int QaAnswerID);

    List<QuestionnaireAnswer> getQuestionnaireAnswerBodiesByUserID(int userID);

    int deleteQuestionnaireAnswerByID(int QaAnswerID);

    int updateQuestionnaireAnswer(QuestionnaireAnswer questionnaireAnswer);
}
