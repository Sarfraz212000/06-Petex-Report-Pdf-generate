package com.petex.serviceimpl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petex.entity.ReportEntity;
import com.petex.repository.ReportRepository;
import com.petex.services.ReportServices;
import com.petex.utils.EmailUtils;
import com.petex.utils.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServicesImpl implements ReportServices {

	@Autowired
	private ReportRepository repo;

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private PdfGenerator pdfGenrator;

	@Override
	public Boolean saveReport(ReportEntity entity) {
		repo.save(entity);
		return true;
	}

	@Override
	public List<ReportEntity> getAllReport() {
		return repo.findAll();

	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		List<ReportEntity> report = repo.findAll();

		if (report.isEmpty()) {
			throw new Exception("No data found");
		}

		File f = new File("report.pdf");

		pdfGenrator.generate(response, report, f);
		String subject = "PET REPORT";
		String body = "PET BODY";
		String to = "bandaruharikrishna1999@gmail.com";

		emailUtils.sendEmail(subject, body, to, f);
		f.delete();
		return true;
	}

}
