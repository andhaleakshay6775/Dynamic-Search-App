package com.Akshay.Rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Akshay.Service.InsuranceService;
import com.Akshay.bindding.SearchRequest;
import com.Akshay.bindding.planResponse;
import com.Akshay.reports.ExcelReportGenrator;
import com.Akshay.reports.pdfReportGenrator;

@RestController
public class InsuranceRestController {
     
	@Autowired
	InsuranceService service;
	
	
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		 response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=plans_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        
		List<planResponse> plans = service.searchPlans(null);
		//ExcelReportGenrator excelReport = new ExcelReportGenrator();
		pdfReportGenrator pdfReport = new pdfReportGenrator();
				pdfReport.exportPdf(plans, response);
	}
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		 response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=plans_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	        
		List<planResponse> plans = service.searchPlans(null);
		ExcelReportGenrator excelReport = new ExcelReportGenrator();
		excelReport.export(plans, response);
	}
	
	@PostMapping("/plans")
	public ResponseEntity<List<planResponse>> SearchPlan(@RequestBody SearchRequest request){
		List<planResponse> searchPlans =  service.searchPlans(request);
		
		return new ResponseEntity<>(searchPlans, HttpStatus.OK);
		
	}
	
	@GetMapping("/plannames")
	public ResponseEntity<List<String>> gatePlanNames(){
		List<String> planNames = service.getUniquePlanNames();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	
	}
	@GetMapping("/planstaus")
	public ResponseEntity<List<String>> gatePlanStatus(){
		List<String> status = service.getUniquePlanStatues();
		return new ResponseEntity<>(status, HttpStatus.OK);
	
	}
}
