package com.kosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.model.DeptDAO;

@Controller
public class Deptcontriller {
	@Autowired
	DeptDAO deptDAO;

	@Autowired
	@RequestMapping("dept/alldeptlist.do")
	public String alldeptlist(Model model){
	
		model.addAttribute("dlist", deptDAO.selectAll());
		return "dept/alldept";
	}
	
	@RequestMapping("dept/deptinsert.do")
	public String empinsert(Model model) {
		model.addAttribute("dlist", deptDAO.selectAll());
		model.addAttribute("mlist", deptDAO.selectAllManager());
		model.addAttribute("llist", deptDAO.selectAllLocation());
		return "dept/deptinsert"; // forward 생략

	}
}
