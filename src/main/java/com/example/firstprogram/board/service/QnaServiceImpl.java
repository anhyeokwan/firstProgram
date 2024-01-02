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
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class QnaServiceImpl implements QnaService{

    private final QnaRepository qnaRepository;
    private final FileRepository fileRepository;

    @Override
    public Map<String, Object> insertQna(Map<String, Object> qnaMap) {

        Map<String, Object> resultMap = new HashMap<>();

        String code = "200";
        String message = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = dateFormat.format(new Date());

        int qnaNum = qnaRepository.findQnaMaxIdx() + 1;

        String title = (String) qnaMap.get("qnaTitle");
        String writer = (String) qnaMap.get("qnaWriter");
        String content = (String) qnaMap.get("qnaContent");
        String secretStatus = (String) qnaMap.get("secretStatus");
        if (secretStatus == null) {
            secretStatus = "N";
        }
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
            JSONObject fileObj = (JSONObject) qnaMap.get("fileObj");
            List<String> filenameList = (ArrayList<String>) fileObj.get("filenameArr");
            List<String> filepathList = (ArrayList<String>) fileObj.get("filepathArr");

            if (filenameList.size() > 0 && filepathList.size() > 0) {
                if (filenameList.size() == filepathList.size()) {
                    for (int i = 0; i < filenameList.size(); i++) {
                        result.addFile(filenameList.get(i), filepathList.get(i), result.getIdx());
                    }
                }else{
                    code = "500";
                    message = "등록 중 에러가 발생하였습니다. 다시 시도해주세요.";
                }
            }
            resultMap.put("qnaIdx", result.getIdx());
        }else {
            code = "500";
            message = "등록 중 에러가 발생하였습니다. 다시 시도해주세요.";
        }

        resultMap.put("code", code);
        resultMap.put("message", message);

        return resultMap;
    }

    @Override
    public Map<String, Object> loadOneQna(int qIdx) {

        Map<String, Object> map = new HashMap<>();

        String code = "200";
        String message = "";

        Optional<Qna> resultQna = qnaRepository.findById(qIdx);

        Qna qna = resultQna.orElseThrow();

        log.info("qna >>> " + qna);

        return null;
    }
}
