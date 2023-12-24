package com.example.firstprogram.board.repository;

import com.example.firstprogram.board.repository.custom.QnaCustom;
import com.example.firstprogram.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface QnaRepository extends JpaRepository<Qna, Integer>, QnaCustom {

    /*public String findTopByIdx(); jpa 메소드를 쓰고 싶다면 파라미터 한개 이상의 값을 가지고 있어야함*/

}
