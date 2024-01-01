package com.example.firstprogram.board.service;

import com.example.firstprogram.entity.Qna;

import java.util.Map;

public interface QnaService {
    Map<String, Object> insertQna(Map<String, Object> qnaMap);

    Map<String, Object> loadOneQna(int qIdx);
}
