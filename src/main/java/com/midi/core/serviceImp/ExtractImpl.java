package com.midi.core.serviceImp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.midi.core.metier.*;
import com.midi.core.service.Extract;
import com.midi.util.ExportExcel;

@Service
public class ExtractImpl implements Extract{

	@Override
	public ArrayList<employee> extractEmp() {
		ArrayList<employee> emps =  new ArrayList<employee>();
		
		String path = "C:\\Users\\buddha\\Desktop\\stage\\employee.xlsx" ;
		   FileInputStream file = null;
			try {
				file = new FileInputStream(new File(path));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      Workbook workbook = null;
			try {
				workbook = new XSSFWorkbook(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      Sheet sheet = workbook.getSheetAt(0);
		      int i = 0;
		      for (Row row : sheet)  {
			      i = row.getRowNum();
			      if (i>0) {
			    	  try {
						employee emp = new employee(row.getCell(0).toString(),row.getCell(1).toString(),row.getCell(2).toString(),new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(row.getCell(3).toString()),row.getCell(4).toString());
						emps.add(emp);
			    	  } catch (ParseException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
			    	  }
			      }
		      }
		return emps;
	}

	@Override
	public evaluation extractEval() {
		String path = "C:\\Users\\buddha\\Desktop\\stage\\example1.xlsx" ;
	      FileInputStream file = null;
		try {
			file = new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      Sheet sheet = workbook.getSheetAt(0);
	      int i = 0;
	      evaluation eval = new evaluation("eval1");
	      List<role> O= new ArrayList<role>();
	      List<critere> C= new ArrayList<critere>();

	      for (Row row : sheet)  {
		      i = row.getRowNum();
		      if (i>0) {
		    	  Scritere scrit = new Scritere(row.getCell(2).toString(),row.getCell(4).toString());
		    	  critere crit = new critere(row.getCell(1).toString(),row.getCell(3).toString());
		    	  role obj = new role(row.getCell(0).toString());


		    	  if(!C.contains(crit)) {
			    	  crit.addScritere(scrit);
			    	  C.add(crit);

			    	  if(!O.contains(obj)) {
			    		  obj.addCritere(crit);
			    		  O.add(obj);
			    	  }else {
			    		  getobjectif(O,obj).addCritere(crit);
			    	  }
			    	  if(!eval.getRoles().contains(obj)) {
				    	  eval.addObjectif(obj);
			    	  }
		    	  }else {
		    		  getcritere(C,crit).addScritere(scrit);
		    	  }
		    	  
		      }
	      }
	      return eval;

	}
	
	
	
	
	
	private static critere getcritere(List<critere> c,critere old) {
		for(critere item : c) {
			if (item.equals(old)) return item;
			
		}
		return null;
	}
	private static role getobjectif(List<role> c,role old) {
		for(role item : c) {
			if (item.equals(old)) return item;
			
		}
		return null;
	}

	@Override
	public byte[] getFile(evaluation eval, List<employee> emps) {
		
		
		
		
		return null;
	}

}
