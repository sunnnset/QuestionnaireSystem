package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswer {
    int answerID;				    // 回答ID
    int answerOrder;				// 对应的问题ID
    int questionnaireAnswerID;	    // 对应的问卷问题ID

    String answer;				    // 存储每个问题对应的回答
    // 对于开放题，存储回答
    // 对于选择题，存储选项的字符串
    // 对于文件题，存储文件名
}
