package com.xy.mapper;

import com.xy.pojo.QuestionAnswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionAnswerMapper {

    int addQA(QuestionAnswer questionAnswer);

    int addQAs(List<QuestionAnswer> list);

    int deleteQA(int ID);

    int updateQA(QuestionAnswer questionAnswer);

    QuestionAnswer getQAByID(int ID);

    List<QuestionAnswer> getQAsByQuestionnaireAnswerID(int questionnaireAnswerID);

}
