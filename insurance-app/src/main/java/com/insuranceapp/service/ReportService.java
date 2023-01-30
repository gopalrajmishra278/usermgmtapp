package com.insuranceapp.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.insuranceapp.entities.CustomerPlan;
import com.insuranceapp.entities.SearchRequest;



public interface ReportService {
	
	
	public List<CustomerPlan> getPlanNames();
	
	public List<CustomerPlan> getPlanStatues();
	
    public List<CustomerPlan> getCustomerPlans(SearchRequest request);
	
    public void exportExcel(HttpServletResponse response) throws Exception;
	
	public void exportPdf(HttpServletResponse response) throws Exception;
	
}
