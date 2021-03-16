package com.xy;

import com.xy.pojo.QuestionAnswer;
import com.xy.service.QuestionAnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionAnswerTest {
    @Autowired
    private QuestionAnswerService service;

    @Test
    public void addQuestionAnswerTest(){

        QuestionAnswer questionAnswer = new QuestionAnswer(0, 1, 1, "测试回答");
        int i = service.addQA(questionAnswer);
        System.out.println(i);
    }

    @Test
    public void queryQuestionAnswerTest(){
        QuestionAnswer questionAnswer = service.getQAByID(2);
        System.out.println(questionAnswer);
    }

    @Test
    public void updateQuestionAnswerTest(){
        QuestionAnswer questionAnswer = service.getQAByID(2);
        questionAnswer.setAnswer("修改回答测试");
        service.updateQA(questionAnswer);
    }

    @Test
    public void deleteQuestionAnswerTest(){
        service.deleteQA(2);
    }
}
