package com.xy.mapper;

import com.xy.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {

    // 增加问题
    int addQuestion(Question question);

    // 增加一组问题
    int addQuestions(List<Question> questions);

    // 按id删除问题
    int deleteQuestionByID(@Param("questionID") Integer questionID);

    // 更新问题
    int updateQuestion(Question question);

    // 根据问题id，获取问题
    Question queryQuestionByID(@Param("questionID") Integer questionID);

    // 根据问卷id，获取某个问卷的所有问题
    List<Question> queryQuestionsByQnaireID(Integer qnaireID);
}
