package com.midi.core.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class settingController {

	@RequestMapping(value={"/setting","/"})
	public String getSeting() {
		
		return "setting";
	}
	
	@RequestMapping(value="/setting/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String AddExcel(@RequestParam("CritExcel") MultipartFile eval,@RequestParam("EmpExcel") MultipartFile emp) {
		/*String content = new String(multipart.getBytes(), StandardCharsets.UTF_8);
		String etat = ImportDataService.importData(content);
		System.out.println(etat);*/
		
		return "evaluation";
	}
}
