package com.xy.controller;


import com.xy.exceptionHandle.MessageResponse;
import com.xy.exceptionHandle.MsgEnum;
import com.xy.exceptionHandle.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
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
    public MessageResponse fileUploadTest(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {throw new ServerException(MsgEnum.EMPTY_FILE);}
        String fileName = file.getOriginalFilename();
        String filePath = ResourceUtils.getURL("classpath:").getPath();
        File fileRecv = new File(filePath+"/file/"+fileName);
        if (!fileRecv.getParentFile().exists())
            fileRecv.getParentFile().mkdir();
        System.out.println("文件上传路径："+fileRecv.getAbsolutePath());
        fileRecv.createNewFile();
        file.transferTo(fileRecv);

        return new MessageResponse(MsgEnum.UPLOAD_SUCCESS);
    }

    @RequestMapping("/msgResponse")
    @ResponseBody
    public MessageResponse respTest(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", "q2345trsdhguisdfhg3q4908");
        return new MessageResponse(MsgEnum.CODE_TEST, map);
    }
}
