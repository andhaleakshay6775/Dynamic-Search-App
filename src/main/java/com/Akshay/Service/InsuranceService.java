package com.Akshay.Service;

import java.util.List;

import com.Akshay.bindding.SearchRequest;
import com.Akshay.bindding.planResponse;

public interface InsuranceService {

	public List<planResponse> searchPlans(SearchRequest request);
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatues();
}
