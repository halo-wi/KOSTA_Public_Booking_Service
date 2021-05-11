package com.kosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		return "emp/empinsert"; // forward 생략

	}

	@RequestMapping(value = "emp/empinsert.do", method = RequestMethod.POST)
	public String empinsertPost(EmpVO emp) {
		empDAO.insertEmp(emp);

		return "redirect:/emp/emplist.do";
	}

	@RequestMapping("/emp/empDetail.do")
	public String empDetailGet(Model model, int empid) {
		model.addAttribute("dlist", deptDAO.selectAll());
		model.addAttribute("mlist", deptDAO.selectAllManager());
		model.addAttribute("jlist", empDAO.selectAllJobs());
		model.addAttribute("emp", empDAO.selectById(empid));
		return "emp/empdetail";
	}
	
	@RequestMapping(value = "/emp/empDetail.do", method = RequestMethod.POST)
	public String empDetailPost(EmpVO emp) {
		empDAO.updateEmp(emp);
		System.out.println("?");
		return "redirect:/emp/emplist.do";
	}
	@RequestMapping("/emp/empDelete.do")
	public String empDeleteGet(int empid) {
		empDAO.deleteEmp(empid);
		return "redirect:/emp/emplist.do";
	}

}
