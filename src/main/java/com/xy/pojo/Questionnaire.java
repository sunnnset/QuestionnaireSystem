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
public class Questionnaire {
    Integer questionnaireID;	// 问卷ID
    String  title; 				// 标题
    String description; 		// 描述
    Date createDate;			// 发表日期

    Boolean needLogin;          // 是否需要登录后填写
    Integer userID;


    List<Question> questions; 	// 存储问题的列表
}
