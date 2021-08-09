package com.core.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.midi.core.metier.*;
import com.midi.core.metier.Scritere;
import com.midi.core.metier.critere;
import com.midi.core.metier.evaluation;

public class main {

	public static void main(String[] args) throws IOException {
	      String path = "C:\\Users\\buddha\\Desktop\\stage\\example1.xlsx" ;
	      FileInputStream file = new FileInputStream(new File(path));
	      Workbook workbook = new XSSFWorkbook(file);
	      Sheet sheet = workbook.getSheetAt(0);
	      int i = 0;
	      evaluation eval = new evaluation("eval1");
	      
	}

}
