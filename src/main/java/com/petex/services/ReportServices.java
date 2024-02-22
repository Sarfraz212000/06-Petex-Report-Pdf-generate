package com.petex.services;

import java.util.List;

import com.petex.entity.ReportEntity;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportServices {
	
	public Boolean saveReport(ReportEntity entity);
	
	public List<ReportEntity> getAllReport();
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;

}
