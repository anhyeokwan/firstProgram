package com.example.firstprogram.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class fileController {

    @ResponseBody
    @PostMapping(value = "/editorFileUpload", produces = "application/json;charset=utf-8")
    public String editorFileUpload(HttpServletRequest request, MultipartFile[] multipartFiles){
        return "";
    }

}
