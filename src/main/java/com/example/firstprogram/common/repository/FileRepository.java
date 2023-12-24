package com.example.firstprogram.common.repository;

import com.example.firstprogram.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}
