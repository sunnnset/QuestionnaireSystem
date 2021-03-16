package com.xy.controller;

import com.xy.exceptionHandle.MessageResponse;
import com.xy.exceptionHandle.MsgEnum;
import com.xy.pojo.QuestionAnswer;
import com.xy.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/answer")
public class QuestionAnswerController {
    @Autowired
    private QuestionAnswerService service;

    @RequestMapping("/get")
    public QuestionAnswer getQuestionAnswerByID(int id){
        return service.getQAByID(id);
    }

    @RequestMapping("/update")
    public MessageResponse updateQuestionAnswer(@RequestBody QuestionAnswer questionAnswer){
        service.updateQA(questionAnswer);
        return new MessageResponse(MsgEnum.SUCCESS);
    }

    @RequestMapping("/delete")
    public MessageResponse deleteQuestionAnswer(int id){
        service.deleteQA(id);
        return new MessageResponse(MsgEnum.SUCCESS);
    }

    @RequestMapping("/add")
    public MessageResponse addQuestionAnswer(@RequestBody QuestionAnswer questionAnswer){
        service.addQA(questionAnswer);
        return new MessageResponse(MsgEnum.SUCCESS);
    }

    // TODO: 其实可以不做，而是放在questionnaireAnswer的service中调用相关mapper的接口
    @RequestMapping("/addByGroup")
    public MessageResponse addQuestionAnswers(@RequestBody List<QuestionAnswer> questionAnswers){
        service.addQAs(questionAnswers);
        return new MessageResponse(MsgEnum.SUCCESS);
    }

}
