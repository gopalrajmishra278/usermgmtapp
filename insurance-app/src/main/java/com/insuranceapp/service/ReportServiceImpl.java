package com.insuranceapp.service;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

port org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.insuranceapp.entities.CustomerPlan;
import com.insuranceapp.entities.SearchRequest;
import com.insuranceapp.repositories.CustomerPlanRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;

import com.lowagie.text.Paragraph;import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private CustomerPlanRepository cpRepo;
	
	@Override
	public List<CustomerPlan> getPlanNames(){
		
		return cpRepo.getPlanNames();
		
	}
	
	@Override
	public List<CustomerPlan> getPlanStatues(){
		
		return cpRepo.getPlanStatues();
		
	}
	
	
	@Override
	public List<CustomerPlan> getCustomerPlans(SearchRequest request) {
		
       CustomerPlan entity = new CustomerPlan();
       
       
       if(request.getPlanName()!=null && !request.getPlanName().equals("")) {
    	   
    	   entity.setPlanName(request.getPlanName());
    	   
       }
		
      if(request.getPlanStatus()!=null && !request.getPlanStatus().equals("")) {
    	  
    	  entity.setPlanStatus(request.getPlanStatus());
      }
      
   
      
	
      Example<CustomerPlan> example = Example.of(entity);
      
      List<CustomerPlan> records = cpRepo.findAll(example);
      
		
	  return records;
	}
	
	@Override
	public void exportExcel(HttpServletResponse response) throws IOException {
		
		List<CustomerPlan> records = cpRepo.findAll();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("Citizen Info");
		
		XSSFRow headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Plan Name");
		headerRow.createCell(2).setCellValue("Plan Status");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("Name");
		headerRow.createCell(5).setCellValue("Email");
		headerRow.createCell(6).setCellValue("Gender");
		headerRow.createCell(7).setCellValue("SSN");
		
		List<CustomerPlan> records = cpRepo.findAll();
		
		
		int dataRowIndex = 1;
		
		for(CustomerPlan record : records) {
			
			XSSFRow dataRow = sheet.createRow(dataRowIndex);
			
			
			dataRow.createCell(0).setCellValue(record.getCustomerId());
			dataRow.createCell(1).setCellValue(record.getPlanName());
			dataRow.createCell(2).setCellValue(record.getPlanStatus());
			dataRow.createCell(3).setCellValue(record.getGender());
			dataRow.createCell(4).setCellValue(record.getCustomerName());
			dataRow.createCell(5).setCellValue(record.getCustomerEmail());
			dataRow.createCell(6).setCellValue(record.getGender());
			dataRow.createCell(7).setCellValue(record.getSsn());
			
			dataRowIndex++;
			
		}
		
		
		ServletOutputStream outputStream = response.getOutputStream();
		
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
		}
	
	
	@Override
     public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
		
		List<CustomerPlan> findAll = cpRepo.findAll();
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
	
		
	    Font f = Font.getFont(FontFactory.HELVETICA_BOLD);
		f.setSize(18);
	    f.setColor(Color.BLUE);
		
		Paragraph p = new Paragraph("Citizen Plan Info", f);
		
		p.setFontAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
		
		PdfTable table =new PdfTable(8);
		
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 1.5f, 3.5f, 3.5f, 3.5f, 3.5f});
		table.setSpacingBefore(10);
		
		//Set Table Header data
		
		PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(8);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("PLAN NAME", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("PLAN STATUS", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("GENDER", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("NAME", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("EMAIL", font));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("GENDER", font));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("SSN", font));
        table.addCell(cell); 
		
		
		//Set table data
		
        
        for(user : listUsers) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getEmail());
            table.addCell(user.getFullName());
            table.addCell(user.getRoles().toString());
            table.addCell(String.valueOf(user.isEnabled()));
        }
		
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		
		document.close();
		
	}
}
