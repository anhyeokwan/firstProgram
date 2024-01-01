package com.example.firstprogram.board.controller;

import com.example.firstprogram.board.service.QnaService;
import com.example.firstprogram.entity.Qna;
import com.example.firstprogram.util.Util;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
@Log4j2
public class qnaController {

    private final QnaService qnaService;
    private final Util util;

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

        String code = "200";
        String message = "";

        JSONObject resultObj = new JSONObject();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;

        String title = (String) map.get("qnaTitle");
        String writer = (String) map.get("qnaWriter");
        String content = (String) map.get("qnaContent");
        String secretStatus = (String) map.get("secretStatus");
        String secretPwd = (String) map.get("secretPwd");
        String fileObj = (String) map.get("fileObj");

        if (fileObj != null) {
            try {
                jsonObject = (JSONObject) jsonParser.parse(fileObj);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        Map<String, Object> qnaMap = new HashMap<>();
        qnaMap.put("qnaTitle", util.XssShield(title));
        qnaMap.put("qnaWriter", writer);
        qnaMap.put("qnaContent", util.XssShield(content));
        qnaMap.put("secretStatus", secretStatus);
        qnaMap.put("secretPwd", secretPwd);
        qnaMap.put("fileObj", jsonObject);

        Map<String, Object> qnaInsert = qnaService.insertQna(qnaMap);

        //HashMap<String, Object> fileMap =

        code = (String) qnaInsert.get("code");
        message = (String) qnaInsert.get("message");

        resultObj.put("code", code);
        resultObj.put("message", message);
        if(code.equals("200")) resultObj.put("qnaIdx", qnaInsert.get("qnaIdx"));

        return resultObj;
    }

    @GetMapping("/loadOneQna")
    public String loadOneQna(HttpServletRequest request, Model model, @RequestParam("qnaIdx") String qnaIdx) {

        String code = "200";
        String message = "";
        int qIdx = 0;

        log.info(">>> " + qnaIdx);

        if (qnaIdx == null) {
            code = "503";
            message = "조회 중 오류가 발생하였습니다.";
        }else{
            try {
                qIdx = Integer.parseInt(qnaIdx);
            } catch (NumberFormatException e) {
                code = "503";
                message = "조회 중 오류가 발생하였습니다.";
            }
        }

        if (code.equals("200")) {
            Map<String, Object> resultQna = qnaService.loadOneQna(qIdx);
        }

        //if(qnaIdx )

        return null;
    }
}
