package com.xy;

import com.xy.mapper.QuestionnaireAnswerMapper;
import com.xy.pojo.QuestionAnswer;
import com.xy.pojo.QuestionnaireAnswer;
import com.xy.service.QuestionnaireAnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class QuestionnaireAnswerServiceTest {
    @Autowired
    private QuestionnaireAnswerService service;

    @Test
    public void getQaAnswerTest(){
        QuestionnaireAnswer questionnaireAnswerByID = service.getQuestionnaireAnswerByID(1);
        System.out.println(questionnaireAnswerByID);
        QuestionnaireAnswer questionnaireAnswerBodyByID = service.getQuestionnaireAnswerBodyByID(1);
        System.out.println(questionnaireAnswerBodyByID);
    }

    @Test
    public void addFullQaAnswerTest(){
        ArrayList<QuestionAnswer> questionAnswers = new ArrayList<>();
        questionAnswers.add(new QuestionAnswer(0, 1, 0, "回答111"));
        questionAnswers.add(new QuestionAnswer(0, 2, 0, "回答222"));
        questionAnswers.add(new QuestionAnswer(0, 3, 0, "回答333"));
        QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer(0, 21, 5, new Date(), questionAnswers);
        service.addFullQuestionnaireAnswer(questionnaireAnswer);
    }

    @Test
    public void deleteQaAnswerTest(){
        service.deleteQuestionnaireAnswerByID(5);
    }

    @Test
    public void updateQaAnswerTest() {
        QuestionnaireAnswer questionnaireAnswerByID = service.getQuestionnaireAnswerByID(7);
        questionnaireAnswerByID.setUserID(1);
        service.updateQuestionnaireAnswer(questionnaireAnswerByID);
    }
}
