package com.petex.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.petex.entity.ReportEntity;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {

	public void generate(HttpServletResponse response, List<ReportEntity> entities, File f)
			throws DocumentException, IOException {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		

		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(20);

		Paragraph paragraph = new Paragraph("PETEX REPORT", fontTitle);

		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(12);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {3.5f, 6.5f, 6.5f, 6.5f, 6.5f, 6.5f, 6.0f, 6.5f,  6.5f,  6.5f, 6.0f, 6.0f});
		table.setSpacingBefore(5);

		table.addCell("PET ID");	
		table.addCell("USER NAME");
		table.addCell("USER EMAIL");
		table.addCell("ADDRESS");
		table.addCell("USER MOBILE");
		table.addCell("PET NAME");
		table.addCell("PET TYPE");
		table.addCell("PET BREED");
		table.addCell("PET COLOR");
		table.addCell("DATE-OF-LOST");
		table.addCell("DATE");
		table.addCell("TIME");

		for (ReportEntity entity : entities) {
			table.addCell(String.valueOf(entity.getPetId()));
			table.addCell(entity.getUserName());
			table.addCell(entity.getUserEmail());
			table.addCell(entity.getAddress());
			table.addCell(String.valueOf(entity.getUserMoblieNumber()));
			table.addCell(entity.getPetName());
			table.addCell(entity.getPetType());
			table.addCell(entity.getPetBreed());
			table.addCell(entity.getPetColour());
			table.addCell(entity.getDateOfLost() != null ? entity.getDateOfLost().toString() : "");
			table.addCell(entity.getDate() != null ? entity.getDate().toString() : "");
			table.addCell(entity.getTimes() != null ? entity.getTimes().toString() : "");
		}

		document.add(table);
		document.close();
	}
}
