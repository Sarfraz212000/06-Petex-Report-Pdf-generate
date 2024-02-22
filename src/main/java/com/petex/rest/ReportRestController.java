package com.petex.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petex.entity.ReportEntity;
import com.petex.services.ReportServices;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/petex")
public class ReportRestController {

	@Autowired
	private ReportServices services;

	@PostMapping("/reportSave")
	public ResponseEntity<String> saveReport(@RequestBody ReportEntity entity) {

		Boolean status = services.saveReport(entity);
		if (status) {
			return new ResponseEntity<>("Report submit successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Report not submit successfully", HttpStatus.CREATED);
	}

	@GetMapping("/getAllReport")
	public ResponseEntity<List<ReportEntity>> getAllReportData() {
		List<ReportEntity> allReport = services.getAllReport();
		return new ResponseEntity<List<ReportEntity>>(allReport, HttpStatus.OK);
	}

	@GetMapping("/pdf")
	public ResponseEntity<String> pdfExport(HttpServletResponse response) throws Exception {

		response.setContentType("application/pdf");
		String headerkey = "content-disposition";
		String headerValue = "attchement;filename=report.pdf";
		response.addHeader(headerkey, headerValue);
		boolean status = services.exportPdf(response);

		if (status) {
			return new ResponseEntity<String>("pdf has sent in your mail", HttpStatus.OK);
		}
		return new ResponseEntity<String>("pdf has not sent in your mail", HttpStatus.OK);

	}

}
