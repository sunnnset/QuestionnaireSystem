package com.xy;

import com.xy.pojo.Question;
import com.xy.service.Impl.QuestionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class QuestionServiceTest {
    /*
    @Test
    public void QuestionAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionServiceImpl questionService = context.getBean("QuestionServiceImpl", QuestionServiceImpl.class);
        Question question = new Question(null, "这是一个所有字段都非空的测试问题",
                "OP", "String", true,
                "choice1:choice2:choice3", "jpg", 6, 1);
        questionService.addQuestion(question);
    }*/

    @Test
    public void QuestionRead(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionServiceImpl questionService = context.getBean("QuestionServiceImpl", QuestionServiceImpl.class);
        Question question = questionService.queryQuestionByID(9);
        System.out.println("---------------");
        System.out.println(question);
        System.out.println("---------------");
    }

    @Test
    public void QuestionDelete(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionServiceImpl questionService = context.getBean("QuestionServiceImpl", QuestionServiceImpl.class);
        questionService.deleteQuestionByID(8);
    }

    @Test
    public void QuestionUpdate(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionServiceImpl questionService = context.getBean("QuestionServiceImpl", QuestionServiceImpl.class);
        Question question = questionService.queryQuestionByID(9);
        System.out.println("----------修改前：--------");
        System.out.println(question);
        question.setChoices("choice4:choice5:choice6");
        question.setQuestionOrder(1000);
        System.out.println(question);
        questionService.updateQuestion(question);
        Question newQuestion = questionService.queryQuestionByID(9);
        System.out.println("----------修改后：--------");
        System.out.println(newQuestion);
    }

    /*
    @Test
    public void QuestionSetAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionServiceImpl questionService = context.getBean("QuestionServiceImpl", QuestionServiceImpl.class);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question(null, "这是一个所有字段都非空的测试问题1",
                "OP", "String", true,
                "choice1:choice2:choice3", "jpg", 6, 1));
        questions.add(new Question(null, "这是一个所有字段都非空的测试问题2",
                "OP", "String", true,
                "choice1:choice2:choice3", "jpg", 6, 1));
        questions.add(new Question(null, "这是一个所有字段都非空的测试问题3",
                "OP", "String", true,
                "choice1:choice2:choice3", "jpg", 6, 1));

        System.out.println(questions);

        questionService.addQuestions(questions);

    }*/

    @Test
    public void QuestionsSetQueryTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionServiceImpl questionService = context.getBean("QuestionServiceImpl", QuestionServiceImpl.class);

        List<Question> questions = questionService.queryQuestionsByQnaireID(7);
        for (Question question : questions) {
            System.out.println(question);
        }

    }
}
