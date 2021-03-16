package com.xy.mapper;

import com.xy.pojo.Questionnaire;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionnaireMapper {

    // 增加问卷
    int addQuestionnaire(Questionnaire questionnaire);

    // 根据问卷id，查询问卷
    Questionnaire queryQuestionnaireByID(int ID);

    Questionnaire queryQuestionnaireBodyByID(int ID);

    List<Questionnaire> queryQuestionnaireBodyByUserID(int userID);

    // 删除问卷
    int deleteQuestionnaireByID(int ID);

    int updateQuestionnaire(Questionnaire questionnaire);
}
