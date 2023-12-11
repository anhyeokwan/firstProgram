package com.example.firstprogram.controller;

import com.example.firstprogram.util.Util;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
public class fileController {

    private final Util util;

    // consumes = MediaType.MULTIPART_FORM_DATA_VALUE : swagger 상에서 파일 전송 가능
    @ResponseBody
    @PostMapping(value = "/editorFileUpload", produces = "application/json;charset=utf-8")
    public String editorFileUpload(HttpServletRequest request, @RequestParam("files") MultipartFile[] files){

        String savePath = "/editor/qna/";

        JSONObject fileJson = util.fileUpload(request, files, savePath);

        String filepath = (String) fileJson.get("filepath");
        return filepath;
    }

    @ResponseBody
    @PostMapping(value = "/fileUpload", produces = "application/json;charset=utf-8")
    public JSONObject fileUpload(HttpServletRequest request, @RequestParam Map<String, Object> map
    , @RequestParam("fileArr") MultipartFile[] multipartFiles) {

        String type = (String) map.get("type");
        //MultipartFile[] multipartFiles = (MultipartFile[]) map.get("fileArr");
        String savePath = "/qna/";
        log.info("type : " + type);
        JSONObject fileJson = util.fileUpload(request, multipartFiles, savePath);
        String filepath = (String) fileJson.get("filepath");
        log.info("filepath : " + filepath);
        for (MultipartFile files : multipartFiles) {
            log.info("files : " + files.getOriginalFilename());
        }

        return null;
    }

}
