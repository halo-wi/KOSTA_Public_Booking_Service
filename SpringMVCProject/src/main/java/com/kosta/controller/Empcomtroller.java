package com.kosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.business.DeptServiceInterface;
import com.kosta.business.EmpService;
import com.kosta.business.EmpServiceImpl;
import com.kosta.model.EmpVO;

@Controller
public class Empcomtroller {

	@Autowired
	EmpService empservice;
	@Autowired
	DeptServiceInterface deptservice;
	
	@RequestMapping("emp/emplist.do")
	public String emplist(Model model) {
		model.addAttribute("emplist", empservice.selectAll());
		return "emp/emplist";

	}

	@RequestMapping("emp/empinsert.do")
	public String empinsert(Model model) {
		model.addAttribute("dlist", deptservice.findAll());
		model.addAttribute("mlist", deptservice.findAllManager());
		model.addAttribute("jlist", empservice.selectAllJobs());
		return "emp/empinsert"; // forward 생략

	}

	@RequestMapping(value = "emp/empinsert.do", method = RequestMethod.POST)
	public String empinsertPost(EmpVO emp) {
		empservice.insertEmp(emp);

		return "redirect:/emp/emplist.do";
	}

	@RequestMapping("/emp/empDetail.do")
	public String empDetailGet(Model model, int empid) {
		model.addAttribute("dlist", deptservice.findAll());
		model.addAttribute("mlist", deptservice.findAllManager());
		model.addAttribute("jlist", empservice.selectAllJobs());
		model.addAttribute("emp", empservice.selectById(empid));
		return "emp/empdetail";
	}
	
	@RequestMapping(value = "/emp/empDetail.do", method = RequestMethod.POST)
	public String empDetailPost(EmpVO emp) {
		empservice.updateEmp(emp);
		System.out.println("?");
		return "redirect:/emp/emplist.do";
	}
	@RequestMapping("/emp/empDelete.do")
	public String empDeleteGet(int empid) {
		empservice.deleteEmp(empid);
		return "redirect:/emp/emplist.do";
	}

}
