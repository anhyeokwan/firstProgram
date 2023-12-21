package com.example.firstprogram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Qna {

    @Id
    @Column(name = "idx")
    private int idx;

    @Column(name = "qna_title")
    private String qnaTitle;

    @Column(name = "qna_writer")
    private String qnaWriter;

    @Column(name = "secret_status")
    private String secretStatus;

    @Column(name = "secret_pwd")
    private String secretPwd;

    @Column(name = "qna_content")
    private String qnaContent;

    @Column(name = "status")
    private String status;

    @Column(name = "reg_date")
    private LocalDate regDate;
}
