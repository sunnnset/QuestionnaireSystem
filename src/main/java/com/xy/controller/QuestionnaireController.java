package com.xy.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy.exceptionHandle.MessageResponse;
import com.xy.exceptionHandle.MsgEnum;
import com.xy.exceptionHandle.ServerException;
import com.xy.pojo.Questionnaire;
import com.xy.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService service;

    // 按问卷id获取问卷，包括问卷下的所有问题
    // 已测试
    @RequestMapping("/get")
    public Questionnaire getFullQuestionnaire(int id) throws JsonProcessingException {
        Questionnaire questionnaire = service.queryQuestionnaireByID(id);
        if (questionnaire == null) { throw new ServerException(MsgEnum.ITEM_NOT_FOUND); }
        return questionnaire;
    }

    // 按id获取问卷，仅查询问卷，不查询问题
    // 已测试
    @RequestMapping("/getBody")
    public Questionnaire getQuestionnaireBody(int id) throws JsonProcessingException {
        Questionnaire questionnaire = service.queryQuestionnaireBodyByID(id);
        if (questionnaire == null) { throw new ServerException(MsgEnum.ITEM_NOT_FOUND); }
        return questionnaire;
    }

    // 按用户ID，获取某用户创建的所有问卷（不包括问题）
    // TODO: 或许应该限制用户只能查询自己的问卷信息？
    @RequestMapping(value = "/getBodiesByUID")
    public List<Questionnaire> getQuestionnaireBodiesByUserID(int uid){
        List<Questionnaire> list = service.queryQuestionnaireBodyByUserID(uid);
        if (list == null) { throw new ServerException(MsgEnum.ITEM_NOT_FOUND); }
        return list;
    }

    // 由接收到的JSON添加问卷（及其问题）
    // 已测试
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> addQuestionnaire(@RequestBody Questionnaire questionnaire) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(questionnaire);
        int qid = service.addFullQuestionnaire(questionnaire);
        map.put("qid", qid);
        map.put("status", 200);
        map.put("msg", "success");
        return map;
    }

    // 按照问卷id，删除指定的问卷（及其对应的问题）
    // 已测试
    @RequestMapping("/delete")
    public MessageResponse deleteQuestionnaire(int id){
        service.deleteQuestionnaireByID(id);
        return new MessageResponse(MsgEnum.SUCCESS);
    }

    // 更新问卷（只更新问题自己的字段，不包括问题）
    // 已测试
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public MessageResponse updateQuestionnaire(@RequestBody Questionnaire questionnaire){
            System.out.println(questionnaire);
            service.updateQuestionnaire(questionnaire);
            return new MessageResponse(MsgEnum.SUCCESS);
    }
}
