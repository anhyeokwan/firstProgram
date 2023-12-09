package com.example.firstprogram.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna")
@Log4j2
public class qnaController {

    @GetMapping("qnaList")
    public String qnaList(){
        return "board/qna/qnaList";
    }

    @GetMapping("/qnaFrm")
    public String qnaFrm(){
        return "/board/qna/qnaFrm";
    }

}
