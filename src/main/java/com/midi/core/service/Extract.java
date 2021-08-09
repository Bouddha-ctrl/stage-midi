package com.midi.core.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.midi.core.metier.*;

public interface Extract {

	public List<employee> extractEmp();
	public evaluation extractEval();
	public byte[] getFile(evaluation eval,List<employee> emps) throws Exception;
}
