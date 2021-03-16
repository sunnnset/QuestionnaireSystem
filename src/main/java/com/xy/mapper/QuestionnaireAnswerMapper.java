package com.xy.mapper;

import com.xy.pojo.QuestionnaireAnswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionnaireAnswerMapper {

    QuestionnaireAnswer getQaAnswerByID(int ID);

    QuestionnaireAnswer getQaAnswerBodyByID(int ID);

    // 通过用户ID，获取某用户填写的所有问卷答案
    List<QuestionnaireAnswer> getQaAnswerBodiesByUserID(int userID);

    int addQaAnswerBody(QuestionnaireAnswer qaAnswer);

    int updateQaAnswerBody(QuestionnaireAnswer qaAnswer);

    int deleteQaAnswer(int ID);

}
