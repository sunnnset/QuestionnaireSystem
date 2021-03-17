package com.xy.controller;


import com.xy.exceptionHandle.MessageResponse;
import com.xy.exceptionHandle.MsgEnum;
import com.xy.exceptionHandle.ServerException;
import com.xy.mapper.UserMapper;
import com.xy.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/test")
@Controller
public class TestController {
    @Autowired
    private UserMapper userMapper;

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

    @RequestMapping("/excel")
    @ResponseBody
    public String excelTest(HttpServletResponse response) throws IOException {

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("sheet1");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("userID");
        row.createCell(1).setCellValue("userName");
        row.createCell(2).setCellValue("password");
        row.createCell(3).setCellValue("role");

        int rowNum = 1;

        for (int i = 0; i < 7; i++){
            User user = userMapper.getUserByID(i);
            if (user != null){
                Row currentRow = sheet.createRow(rowNum);
                currentRow.createCell(0).setCellValue(user.getUserID());
                currentRow.createCell(1).setCellValue(user.getUserName());
                currentRow.createCell(2).setCellValue(user.getPassword());
                currentRow.createCell(3).setCellValue(user.getRole());
                rowNum++;
            }
        }

        for (int i = 0; i < 4; i++){
            sheet.autoSizeColumn(i);
        }

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=user.xlsx");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
        return "success";
    }


}
