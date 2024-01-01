package com.example.firstprogram.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_file_qna")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_idx")
    private int fIdx;

    @Column(name = "g_idx")
    private int gIdx;

    @Column(name = "filename")
    private String filename;

    @Column(name = "filepath")
    private String filepath;

}
