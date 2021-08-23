package com.midi.core.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.midi.core.metier.*;

public interface Extract {

	public List<employee> extractEmp(MultipartFile  file) throws Exception;
	public evaluation extractEval(MultipartFile  file) throws Exception;
	public byte[] getFile(evaluation eval,List<employee> emps) throws Exception;
}
