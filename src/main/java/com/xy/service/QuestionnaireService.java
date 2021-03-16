package com.xy.service;

import com.xy.pojo.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    // 将问卷及其问题导入数据库
    int addFullQuestionnaire(Questionnaire questionnaire);

    // 将问卷导入数据库
    int addQuestionnaireWithoutQuestions(Questionnaire questionnaire);

    // 根据ID查询问卷
    Questionnaire queryQuestionnaireByID(int questionnaireID);

    Questionnaire queryQuestionnaireBodyByID(int questionnaireID);

    List<Questionnaire> queryQuestionnaireBodyByUserID(int userID);

    // 根据ID删除问卷
    int deleteQuestionnaireByID(int questionnaireID);

    // 更新问卷
    int updateQuestionnaire(Questionnaire questionnaire);
}
