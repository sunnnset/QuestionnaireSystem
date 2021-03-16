package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    Integer questionID;		// 问题ID
    String description;		// 问题描述
    String questionType;	// 问题类型:OP,CH,FL
    Boolean required;       // 该问题是否必填

    String answerType;		// 回答类型，对应开放类问题：ST,TM,IT;
    Boolean isMultipleChoice;	// 是否多选，对应选择型问题
    String choices;			// 选项，对应选择型问题
    String fileType;		// 文件类型，对应文件型问题

    Integer questionnaireID;	// 问题对应的问卷ID
    Integer questionOrder;		// 问题在问卷中的顺序

}
