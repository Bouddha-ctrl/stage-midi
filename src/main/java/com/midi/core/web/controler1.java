package com.midi.core.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.midi.core.metier.employee;
import com.midi.core.metier.evaluation;
import com.midi.core.service.Extract;
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
	
	
	@RequestMapping("/evaluation")
	public String extractEval(Model model) throws IOException {
		
	    model.addAttribute("evaluation",eval);
		return "interface1";
	}
	
	@RequestMapping("/employee")
	public ModelAndView extractEmp() {
		ModelAndView model = new ModelAndView();

	    model.addObject("emps",emps);

	    model.setViewName("emp");
	    return model;
	}
	
	@RequestMapping("/export/{id}")
	public ResponseEntity<ByteArrayResource> exportExcel(@PathVariable String id,HttpServletResponse response) throws Exception {
		employee emp =null;
		for (employee item : emps) {
			if (item.getMatricule().equals(id)) {
				emp = item;
				break;
			}
		}
		ExportExcel Exporter = new ExportExcel(eval,emp);
		byte[] file = Exporter.export();
		return ResponseEntity.ok()
		.contentType(MediaType.parseMediaType("application/octet-stream"))
		.header(HttpHeaders.CONTENT_DISPOSITION,"attachement; filename="+emp.getNom()+"_"+emp.getPrenom()+".xlsx")
		.body(new ByteArrayResource(file));
	}

	
	@RequestMapping("/exportAll")
	public ResponseEntity<ByteArrayResource> exportAll(@ModelAttribute("emps") ArrayList<employee> emps,HttpServletResponse response) throws Exception {
		emps = (ArrayList<employee>) this.emps;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zipFile = new ZipOutputStream(baos);
		
		baos.toByteArray();
		for (employee emp : emps) {
			ExportExcel Exporter = new ExportExcel(eval,emp);
			byte[] file = Exporter.export();

	        ZipEntry entry = new ZipEntry(emp.getNom()+"_"+emp.getPrenom()+".xlsx");
	        entry.setSize(file.length);
	        zipFile.putNextEntry(entry);
	        zipFile.write(file);
	        zipFile.closeEntry();
		}
		zipFile.finish();
		zipFile.flush();
		zipFile.close();
		return ResponseEntity.ok()
		.contentType(MediaType.parseMediaType("APPLICATION/DOWNLOAD"))
		.header(HttpHeaders.CONTENT_DISPOSITION,"attachement; filename=files.zip")
		.body(new ByteArrayResource(baos.toByteArray()));
	}

}
