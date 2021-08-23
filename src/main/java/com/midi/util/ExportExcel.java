package com.midi.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.midi.core.metier.*;

public class ExportExcel {
	
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private employee emp;
	private List<Integer> critIndex;

	private evaluation eval;
	

	public ExportExcel(evaluation eval,employee emp) {
		this.eval = eval;
		this.emp = emp;
		this.critIndex = new ArrayList<Integer>();
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("test");
	}

	private void writeHeaderRow() {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("role");
		row.createCell(1).setCellValue("critere");
		row.createCell(2).setCellValue("sous critere");
		row.createCell(3).setCellValue("taux crit");
		row.createCell(4).setCellValue("taux s crit");
		row.createCell(5).setCellValue("Note");
		
	}
	
	private void writeDataRows() throws Exception{
		
		role role = null;
		
		for (role item : eval.getRoles()) {
			if (item.getNom().equals(emp.getRole())) {
				role = item; break;
			}
		}
		if(role==null) {
			throw new Exception("Le role "+emp.getRole()+" n'existe pas");
		}
		
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();  
        style.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());  
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        
			Row row1 =sheet.createRow(rowCount);
			row1.createCell(0).setCellValue(role.getNom());
			
			
            
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
            
            row1.createCell(1).setCellStyle(style);
            row1.createCell(2).setCellStyle(style);
            row1.createCell(3).setCellStyle(style);
            row1.createCell(4).setCellStyle(style);
            row1.createCell(5	).setCellStyle(style);

			rowCount++;
			for (critere crit : role.getCriteres()) {
				Row row2 =sheet.createRow(rowCount);
				critIndex.add(rowCount);
				row2.createCell(0).setCellStyle(style);
				row2.createCell(1).setCellValue(crit.getNom());
				row2.createCell(3).setCellValue(crit.getTaux());				
				
				String formula ="";
				for(int index = rowCount+2;index<crit.getScriteres().size()+rowCount+2;index++) {
					formula+="F"+String.valueOf(index)+"*$E$"+String.valueOf(index)+"+";
				}
				formula = formula.substring(0, formula.length() - 1);				
				row2.createCell(5).setCellFormula(formula);

				rowCount++;
				for (Scritere scrit : crit.getScriteres()) {
					Row row3 =sheet.createRow(rowCount);					
					row3.createCell(0).setCellStyle(style);
					row3.createCell(1).setCellStyle(style);
					row3.createCell(2).setCellValue(scrit.getNom());
					row3.createCell(4).setCellValue(scrit.getTaux());
					
					rowCount++;
				}
			}
			Row row5 =sheet.createRow(rowCount);					

			row5.createCell(4).setCellValue("total");
			String formula2 ="";
			for (int index : critIndex) {
				formula2+="F"+String.valueOf(index+1)+"*$D$"+String.valueOf(index+1)+"+";
			}
			formula2 = formula2.substring(0, formula2.length() - 1);				

			row5.createCell(5).setCellFormula(formula2);

			rowCount++;
			Row row4 =sheet.createRow(rowCount);					
			row4.createCell(0).setCellValue(emp.getMatricule());
			row4.createCell(1).setCellValue(emp.getNom());
			row4.createCell(2).setCellValue(emp.getPrenom());
			row4.createCell(3).setCellValue(emp.getDate_debut());
			row4.createCell(4).setCellValue(emp.getRole());
			
		
	}

	
	public byte[] export() throws Exception {
		
		writeHeaderRow();
		writeDataRows();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        workbook.write(baos);
        return baos.toByteArray();
	}
}
