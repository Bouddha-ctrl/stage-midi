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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
		eval = null;
		emps = new ArrayList<employee>();
	}
	
	
	@RequestMapping("/evaluation")
	public String extractEval(Model model,HttpSession session) throws IOException {
		getsessionContent(session);
	    model.addAttribute("evaluation",eval);
		return "interface1";
	}
	
	@RequestMapping("/employee")
	public String extractEmp(Model model,HttpSession session) {

		getsessionContent(session);
		if (eval == null) {
		    model.addAttribute("checkEval",0);
		}else {
		    model.addAttribute("checkEval",1);
		}
	    model.addAttribute("emps",emps);

	    return "emp";
	}
	
	@RequestMapping("employee/export/{id}")
	public ResponseEntity<ByteArrayResource> exportOne(@PathVariable String id,HttpServletResponse response,Model model) throws Exception {
		employee emp =null;
		for (employee item : emps) {
			if (item.getMatricule().equals(id)) {
				emp = item;
				break;
			}
		}
		byte[] file = null;
		ExportExcel Exporter = new ExportExcel(eval,emp);
		try {
			file = Exporter.export();
		}catch(Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return ResponseEntity.ok()
		.contentType(MediaType.parseMediaType("application/octet-stream"))
		.header(HttpHeaders.CONTENT_DISPOSITION,"attachement; filename="+emp.getNom()+"_"+emp.getPrenom()+".xlsx")
		.body(new ByteArrayResource(file));
	}


	@ResponseBody
	@RequestMapping(value="/employee/exportAll", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<ByteArrayResource> export(@RequestBody String body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper(); 
		JsonNode node = null; 
		List<String> Mats = new ArrayList<String>();
		try { node = mapper.readTree(body);
		ArrayNode arrayNode = (ArrayNode) node.get("value");
		arrayNode.forEach(jsonNode -> Mats.add(jsonNode.asText())); 
		}catch(Exception ex) {
			System.out.println("jackson error");
			ex.getStackTrace(); 
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zipFile = new ZipOutputStream(baos);
		

		for (employee emp : emps) {
			if(Mats.contains(emp.getMatricule())){
				ExportExcel Exporter = new ExportExcel(eval,emp);
				byte[] file = Exporter.export();

		        ZipEntry entry = new ZipEntry(emp.getNom()+"_"+emp.getPrenom()+".xlsx");
		        entry.setSize(file.length);
		        zipFile.putNextEntry(entry);
		        zipFile.write(file);
		        zipFile.closeEntry();
			}
			
		}
		zipFile.finish();
		zipFile.flush();
		zipFile.close();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("APPLICATION/DOWNLOAD"))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachement; filename=files.zip")
				.body(new ByteArrayResource(baos.toByteArray()));
	}
	

	
	public void getsessionContent(HttpSession session) {
		if(session.getAttribute("emps") != null) {
			emps = (List<employee>) session.getAttribute("emps");
		}
		if(session.getAttribute("eval") != null) {
			eval = (evaluation) session.getAttribute("eval");
		}
	}
}
