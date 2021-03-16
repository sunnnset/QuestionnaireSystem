package com.xy.service;

import com.xy.pojo.Question;

import java.util.List;

public interface QuestionService {
    // 添加问题
    int addQuestion(Question question);

    // 添加一组问题
    void addQuestions(List<Question> questions);

    // 按照问题id，查询问题
    Question queryQuestionByID(int questionID);

    // 按照对应的问卷id，查找所有该问卷下的问题
    List<Question> queryQuestionsByQnaireID(int questionnaireID);

    // 按照问题id，删除问题
    void deleteQuestionByID(int questionID);

    // 按照问题id，更新问题
    void updateQuestion(Question question);
}
