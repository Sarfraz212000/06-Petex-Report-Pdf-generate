package com.petex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petex.entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Long>{

}
