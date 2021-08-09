package com.midi.core.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.midi.core.metier.Scritere;
import com.midi.core.metier.critere;
import com.midi.core.metier.employee;
import com.midi.core.metier.evaluation;
import com.midi.core.metier.role;
import com.midi.core.service.Extract;
import com.midi.core.serviceImp.ExtractImpl;
import com.midi.util.ExportExcel;

@Controller
public class controler1 {
	
	@Autowired
	private Extract extractService;
	
	private evaluation eval= null;
	private List<employee> emps = null;
	
	
	@PostConstruct
	public void init() {
		eval = extractService.extractEval();
		emps = extractService.extractEmp();
	}
	
	
	@RequestMapping("/")
	public String extractEval(Model model) throws IOException {
		
	    model.addAttribute("evaluation",eval);
		return "interface1";
	}
	
	@RequestMapping("/emp")
	public ModelAndView extractEmp() {
		ModelAndView model = new ModelAndView();

	    model.addObject("emps",emps);

	    model.setViewName("emp");
	    return model;
	}
	
	@RequestMapping("/export/{id}")
	public void exportExcel(@PathVariable String id,HttpServletResponse response) throws Exception {
		System.out.println("contorl");
		employee emp =null;
		for (employee item : emps) {
			if (item.getMatricule().equals(id)) {
				emp = item;
				break;
			}
		}
		ExportExcel Exporter = new ExportExcel(eval,emp);
		Exporter.export(response);
	}

	
	@RequestMapping("/exportAll")
	public void exportAll(@ModelAttribute("emps") ArrayList<employee> emps,HttpServletResponse response) throws Exception {
		System.out.println(emps.size());
		for (employee emp : emps) {
			System.out.println("controller 2");
			ExportExcel Exporter = new ExportExcel(extractService.extractEval(),emp);
			Exporter.export(response);
		}
	}

}
