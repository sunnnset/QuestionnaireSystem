package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireAnswer {
    int answerID;				// 回答ID
    int questionnaireID;		// 对应的问卷ID
    int userID;					// 用户ID，为0代表未登录用户
    Date answerDate;			// 回答日期
    List<QuestionAnswer> answers; 	// 存储每个问题的回答
}
