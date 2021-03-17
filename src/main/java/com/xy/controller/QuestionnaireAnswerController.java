package com.xy.controller;

import com.xy.exceptionHandle.MessageResponse;
import com.xy.exceptionHandle.MsgEnum;
import com.xy.exceptionHandle.ServerException;
import com.xy.pojo.QuestionnaireAnswer;
import com.xy.service.QuestionnaireAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questionnaireAnswer")
public class QuestionnaireAnswerController {
    @Autowired
    private QuestionnaireAnswerService service;

    @RequestMapping("/getBody")
    // 根据问卷回答的id，获取该问卷回答（不包括问题回答）
    public QuestionnaireAnswer getQuestionnaireAnswerBody(int id){
        QuestionnaireAnswer questionnaireAnswer = service.getQuestionnaireAnswerBodyByID(id);
        if (questionnaireAnswer == null) { throw new ServerException(MsgEnum.ITEM_NOT_FOUND); }
        return questionnaireAnswer;
    }

    @RequestMapping("/get")
    // 根据问卷回答的id，获取该问卷回答
    public QuestionnaireAnswer getQuestionnaireAnswer(int id){
        QuestionnaireAnswer questionnaireAnswer = service.getQuestionnaireAnswerByID(id);
        if (questionnaireAnswer == null) { throw new ServerException(MsgEnum.ITEM_NOT_FOUND); }
        return questionnaireAnswer;
    }

    @RequestMapping("/getBodiesByUID")
    // 根据用户id，获取该用户的所有问卷回答（不包括具体的问题）
    public List<QuestionnaireAnswer> getQuestionnaireAnswerBodiesByUserID(int uid){
        List<QuestionnaireAnswer> list = service.getQuestionnaireAnswerBodiesByUserID(uid);
        if (list == null) { throw new ServerException(MsgEnum.ITEM_NOT_FOUND);}
        return list;
    }

    @RequestMapping("/getByQID")
    public List<QuestionnaireAnswer> getFullQaAnswersByQuestionnaireID(int qid, int num, int offset){
        List<QuestionnaireAnswer> list = service.getFullQaAnswersByQuestionnaireID(qid, num, offset);
        if (list == null) {throw new ServerException(MsgEnum.ITEM_NOT_FOUND);}
        return list;
    }

    @RequestMapping("/delete")
    public MessageResponse deleteQuestionnaireAnswerByID(int id){
        service.deleteQuestionnaireAnswerByID(id);
        return new MessageResponse(MsgEnum.SUCCESS);
    }

    @RequestMapping("/add")
    public MessageResponse addQuestionnaireAnswer(@RequestBody QuestionnaireAnswer questionnaireAnswer){
        System.out.println(questionnaireAnswer);
        service.addFullQuestionnaireAnswer(questionnaireAnswer);
        return new MessageResponse(MsgEnum.SUCCESS);
    }

    @RequestMapping("/update")
    public MessageResponse updateQuestionnaireAnswer(@RequestBody QuestionnaireAnswer questionnaireAnswer){
        service.updateQuestionnaireAnswer(questionnaireAnswer);
        return new MessageResponse(MsgEnum.SUCCESS);
    }
}
