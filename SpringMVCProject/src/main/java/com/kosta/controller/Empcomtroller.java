package com.kosta.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	
	/*@RequestMapping(value = "/emp/login.do", produces={"text/html;charset=utf-8"})
	@ResponseBody //브라우저에 응답문서가 간다 body에 출력
	public String empLogin(int id, String email) {
		EmpVO emp = empservice.loginChk(id, email);
		String message = "XXX";
		if(emp!=null) message = emp.toString();
		return message;} */
		
	
	@RequestMapping("/login/loginFrom.do")
	public String loginFromget() {
		return "/login/loginFrom";
	}	
	
	@RequestMapping(value = "/login/login3.do", method = RequestMethod.POST)
	public String loginPost(int userid, String userpw, HttpSession session) {
		
		EmpVO emp = empservice.loginChk(userid, userpw);
		System.out.println(emp);
		if(emp!=null) {
			session.setAttribute("login", emp);
			return "redirect:/login/loginlist.do";
		}
		else return "redirect:/login/loginFrom.do";
	}
	
	@RequestMapping("/emp/selectByDept.do")
	public String selectByDept(int deptid, Model model) {
		List<EmpVO> emplist = empservice.selectByDept(deptid);
		model.addAttribute("emplist",emplist);
		return "emp/emplist";
		
	}
}
