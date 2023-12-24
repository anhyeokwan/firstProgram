package com.example.firstprogram.board.service;

import com.example.firstprogram.board.repository.QnaRepository;
import com.example.firstprogram.common.repository.FileRepository;
import com.example.firstprogram.entity.Qna;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class QnaServiceImpl implements QnaService{

    private final QnaRepository qnaRepository;
    private final FileRepository fileRepository;

    @Override
    public Map<String, Object> insertQna(Map<String, Object> qnaMap) {

        String code = "200";
        String message = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = dateFormat.format(new Date());

        int qnaNum = qnaRepository.findQnaMaxIdx() + 1;
        log.info("qnaNum : " + qnaNum);

        /*int qnaIdx = 0;
        if (qnaNum == null) {
            qnaIdx = 1;
        }else{
            qnaIdx = qnaNum.getIdx() + 1;
        }*/

        String title = (String) qnaMap.get("qnaTitle");
        String writer = (String) qnaMap.get("qnaWriter");
        String content = (String) qnaMap.get("qnaContent");
        String secretStatus = (String) qnaMap.get("secretStatus");
        String secretPwd = (String) qnaMap.get("secretPwd");

        Qna insertQna = Qna.builder()
                .idx(qnaNum)
                .qnaTitle(title)
                .qnaWriter(writer)
                .qnaContent(content)
                .secretStatus(secretStatus)
                .secretPwd(secretPwd)
                .status("w")
                .regDate(date)
                .build();

        Qna result = qnaRepository.save(insertQna);

        if (result != null) {
            JSONObject fileObj = (JSONObject) qnaMap.get("jsonObject");
            log.info("fileObj >>> " + fileObj);

        }

        return null;
    }
}
