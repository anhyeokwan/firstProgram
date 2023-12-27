package com.example.firstprogram.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_qna")
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
    private String regDate;

    @OneToMany(fetch = FetchType.LAZY
            , cascade = CascadeType.ALL /* persist(영속성)와 remove(삭제)의 기능을 모두 가지고 있음 */
            , orphanRemoval = true /* 부모가 삭제되면 자식도 삭제 */)
    @Builder.Default
    private List<File> files = new ArrayList<>();

    /*
    * 파일 리스트 생성
    * */
    public void addFile(String filename, String filepath, int gIdx) {
        File file = File.builder()
                .gIdx(gIdx)
                .filename(filename)
                .filepath(filepath)
                .type("q")
                .build();

        files.add(file);
    }
}


















