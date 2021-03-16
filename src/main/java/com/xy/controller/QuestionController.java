package com.xy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xy.exceptionHandle.MessageResponse;
import com.xy.exceptionHandle.MsgEnum;
import com.xy.exceptionHandle.ServerException;
import com.xy.pojo.Question;
import com.xy.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionServiceImpl;

    // 按问题id获取问题
    // 已测试
    @RequestMapping("/get")
    public Question getQuestion(int id) throws JsonProcessingException {
        Question question = questionServiceImpl.queryQuestionByID(id);
        if (question == null) {throw new ServerException(MsgEnum.ITEM_NOT_FOUND); }
        return question;
    }

    @RequestMapping("/getTest")
    public MessageResponse getQuestionTest(int id) {
        Question question = questionServiceImpl.queryQuestionByID(id);
        if (question == null) {throw new ServerException(MsgEnum.ITEM_NOT_FOUND); }
        HashMap<String, Object> map = new HashMap<>();
        map.put("question", question);
        return new MessageResponse(MsgEnum.SUCCESS, map);
    }

    // 按问卷id，获取该问卷下的所有问题
    // 已测试
    @RequestMapping("/getByQID")
    public List<Question> getQuestionsByQnaireID(int qaID) throws JsonProcessingException {
        List<Question> questions = questionServiceImpl.queryQuestionsByQnaireID(qaID);
        return questions;
    }

    @RequestMapping("/update")
    public MessageResponse updateQuestion(@RequestBody Question question) {
        System.out.println(question);
        questionServiceImpl.updateQuestion(question);
        return new MessageResponse(MsgEnum.SUCCESS);
    }

    @RequestMapping("/delete")
    public MessageResponse deleteQuestion(int id) {
        questionServiceImpl.deleteQuestionByID(id);
        return new MessageResponse(MsgEnum.SUCCESS);
    }

    @RequestMapping("/add")
    public Map<String, Object> addQuestion(@RequestBody Question question){
        Map<String, Object> map = new HashMap<>();
        int questionID = questionServiceImpl.addQuestion(question);
        map.put("msg", "操作成功");
        map.put("status", 200);
        map.put("questionID", questionID);
        return map;
    }
}
