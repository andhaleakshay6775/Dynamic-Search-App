package com.Akshay.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Akshay.Entities.InsurancePlanEntity;
import com.Akshay.bindding.SearchRequest;
import com.Akshay.bindding.planResponse;
import com.Akshay.repository.InsurancePlanRepository;

@Service
public class InsuranceServiceImpl implements InsuranceService {
   
	  @Autowired
	InsurancePlanRepository insuRepo;
	@Override
	public List<planResponse> searchPlans(SearchRequest request) {
		
		InsurancePlanEntity entity = new InsurancePlanEntity();
		
		if(request!=null &&  request.getPlanName()!=null && !request.getPlanName().equals("")){
			entity.setPlanName(request.getPlanName());
		}
		
		if(request!=null && request.getPlanStatus()!=null && !request.getPlanStatus().equals("") ){
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		Example<InsurancePlanEntity> of = Example.of(entity);
		
		List<InsurancePlanEntity> findAll = insuRepo.findAll(of);
		
		List<planResponse> plans = new ArrayList<>();
		
		for(InsurancePlanEntity plan : findAll) {
			planResponse presp = new planResponse();
			
			BeanUtils.copyProperties(plan, presp);
			plans.add(presp);
		}
		
		return plans;
	}

	@Override
	public List<String> getUniquePlanNames() {
		
		return insuRepo.getPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatues() {
		
		return insuRepo.getPlanStatus();
	}

}
