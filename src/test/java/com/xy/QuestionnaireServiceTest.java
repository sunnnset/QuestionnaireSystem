package com.xy;

import com.xy.pojo.Questionnaire;
import com.xy.service.Impl.QuestionnaireServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuestionnaireServiceTest{

    /*
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionServiceImpl questionService = context.getBean("QuestionServiceImpl", QuestionServiceImpl.class);
        QuestionnaireServiceImpl questionnaireServiceImpl = context.getBean("QuestionnaireServiceImpl", QuestionnaireServiceImpl.class);


        // 测试问卷的导入
        Questionnaire questionnaire = new Questionnaire(null, "测试问卷1", "这是测试问卷1的描述",new Date(), false, 1, null);
        int qID = questionnaireServiceImpl.addQuestionnaireWithoutQuestions(questionnaire);
        System.out.println(qID);

        // 测试问题的导入
        Question question = new Question(null, "这是一个测试问题",
                "OP", null, true,
                "choice1:choice2:choice3", null, qID, 1);
        questionService.addQuestion(question);
    }

    @Test
    public void testQuestionnaireAddWithQuestions(){

        List<Question> questions = new ArrayList<>();
        questions.add(new Question(null, "这是一个多选类问题,用于测试添加问卷",
                "CH", null, true,
                "choice1:choice2:choice3", null, null, 1));
        questions.add(new Question(null, "这是一个单选类问题,用于测试添加问卷",
                "CH", null, false,
                "choice1:choice2:choice3", null, null, 2));
        questions.add(new Question(null, "这是一个开放类问题,用于测试添加问卷",
                "OP", "String", null,
                null, null, null, 3));
        questions.add(new Question(null, "这是一个文件类问题,用于测试添加问卷",
                "FL", null, null,
                null, "jpg", null, 4));

        Questionnaire questionnaire = new Questionnaire(null, "测试问卷", "问卷的描述",new Date(), false, 1, questions);
        // questionnaireServiceImpl.addFullQuestionnaire(questionnaire);
    }*/

    @Test
    public void testQuestionnaireFullAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionnaireServiceImpl questionnaireServiceImpl = context.getBean("QuestionnaireServiceImpl", QuestionnaireServiceImpl.class);

        // questionnaireServiceImpl
    }

    @Test
    public void testDeleteQuestionnaire(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionnaireServiceImpl questionnaireServiceImpl = context.getBean("QuestionnaireServiceImpl", QuestionnaireServiceImpl.class);

        questionnaireServiceImpl.deleteQuestionnaireByID(9);
    }

    @Test
    public void testQueryQuestionnaireBody(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionnaireServiceImpl questionnaireServiceImpl = context.getBean("QuestionnaireServiceImpl", QuestionnaireServiceImpl.class);

        Questionnaire questionnaire = questionnaireServiceImpl.queryQuestionnaireBodyByID(11);
        System.out.println(questionnaire);
    }
}
