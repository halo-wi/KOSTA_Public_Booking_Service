package com.kosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.model.DeptDAO;
import com.kosta.model.EmpDAO;
import com.kosta.model.EmpVO;

@Controller
public class Empcomtroller {

	@Autowired
	EmpDAO empDAO;
	@Autowired
	DeptDAO deptDAO;
	
	@RequestMapping("emp/emplist.do")
	public String emplist(Model model) {
		model.addAttribute("emplist", empDAO.selectAll());
		return "emp/emplist";
		
	}
	@RequestMapping("emp/empinsert.do")
	public String empinsert(Model model) {
		model.addAttribute("dlist", deptDAO.selectAll());
		model.addAttribute("mlist", deptDAO.selectAllManager());
		model.addAttribute("jlist", empDAO.selectAllJobs());
		return "emp/empinsert";
		
	}
}
