package com.Akshay.bindding;

import lombok.Data;

@Data
public class SearchRequest {
	
	private Integer planId;
	   
	private String planName;
   
	private String planHolderName;
   
	private Long planHolderSsn;
    
	private String planStatus;
}
