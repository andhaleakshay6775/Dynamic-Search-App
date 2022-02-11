package com.Akshay.reports;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Akshay.bindding.planResponse;

public class ExcelReportGenrator {
            
	public void export (List<planResponse> plans, HttpServletResponse response) throws Exception {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("Plans");
		
		XSSFRow headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("Plan ID");
		headerRow.createCell(1).setCellValue("Holder Name");
		headerRow.createCell(2).setCellValue("Holder SSN");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		
		for(int i = 0; i < plans.size();i++) {
			planResponse plan = plans.get(i);
			XSSFRow dataRow = sheet.createRow(i+1);
			
			dataRow.createCell(0).setCellValue(plan.getPlanId());
			dataRow.createCell(1).setCellValue(plan.getPlanHolderName());
			dataRow.createCell(2).setCellValue(plan.getPlanHolderSsn());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());
			
		}
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
	}
}
