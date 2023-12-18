package com.example.firstprogram.controller;

import com.example.firstprogram.util.Util;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/qna")
@Log4j2
public class qnaController {

    private Util util;

    @GetMapping("/qnaList")
    public String qnaList() {
        return "board/qna/qnaList";
    }

    @GetMapping("/qnaFrm")
    public String qnaFrm() {
        return "/board/qna/qnaFrm";
    }

    @ResponseBody
    @PostMapping(value = "/insertQna", produces = "application/json;charset=utf-8")
    public JSONObject insertQna(HttpServletRequest request
            , @RequestParam Map<String, Object> map) {
        String title = (String) map.get("qnaTitle");
        String writer = (String) map.get("qnaWriter");
        String content = (String) map.get("qnaContent");
        String fileObj = (String) map.get("fileObj");

        //HashMap<String, Object> fileMap =

        System.out.println("fileObj >>> " + fileObj);

        return null;
    }
}
