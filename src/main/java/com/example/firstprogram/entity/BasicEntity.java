package com.example.firstprogram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@MappedSuperclass // 자식 클래스에게 부모클래스가 가지는 컬럼만 매핑 정보를 제공
@EntityListeners(value = {AuditingEntityListener.class}) // 커스텀 콜백 작성, @CreatedBy(작성자) , @CreatedDate(작성일) @LastModifiedDate(수정일) @LastModifiedBy(수정자) 자동으로 넣어줌 없으면 안됨
@Getter
public abstract class BasicEntity {

    @Column(name = "reg_date", nullable = false)
    @CreatedDate
    private LocalDate regDate;

    @Column(name = "upd_date", nullable = true)
    @LastModifiedDate
    private LocalDate updDate;
}
