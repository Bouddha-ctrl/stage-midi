package com.midi.core.web;

import java.util.ArrayList;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.midi.core.metier.*;
import com.midi.core.service.Extract;

@MultipartConfig 
@Controller
public class settingController {

	@Autowired
	private Extract extractService;
	
	@RequestMapping(value={"/setting","/"})
	public String getSeting() {
		
		return "setting";
	}
	
	@RequestMapping(value="/setting/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String AddExcel(@RequestParam("CritExcel") MultipartFile eval,@RequestParam("EmpExcel") MultipartFile emp, HttpSession session, Model model) throws Exception {
		if(!eval.isEmpty()) {
			try {
				evaluation evalu = extractService.extractEval(eval);
				session.setAttribute("eval", evalu);
			}catch(Exception e){
				model.addAttribute("error","File : "+eval.getOriginalFilename()+", " + e.getMessage());
				return "setting";
			}

		}
		if(!emp.isEmpty()) {
			try {
				ArrayList<employee> emps = (ArrayList<employee>) extractService.extractEmp(emp);
				session.setAttribute("emps", emps);
			}catch(Exception e) {
				model.addAttribute("error","File : "+emp.getOriginalFilename()+", " + e.getMessage());
				return "setting"; 
			}

		}
		return "redirect:/evaluation";
	}
}
