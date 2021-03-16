package com.xy.controller;


import com.xy.exceptionHandle.MessageResponse;
import com.xy.exceptionHandle.MsgEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/test")
@Controller
public class TestController {

    @RequestMapping("/map")
    @ResponseBody
    public Map<String, Object> mapTest(){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("qid", 14);
        map.put("msg", "问卷添加成功");

        return map;
    }

    @GetMapping("/upload")
    public String fileUpload(){
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String fileUploadTest(String fileName, MultipartFile file){

        return "success";
    }

    @RequestMapping("/msgResponse")
    @ResponseBody
    public MessageResponse respTest(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", "q2345trsdhguisdfhg3q4908");
        return new MessageResponse(MsgEnum.CODE_TEST, map);
    }
}
