package com.kosta.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.business.DeptServiceInterface;
import com.kosta.business.EmpService;
import com.kosta.model.EmpVO;
import com.kosta.util.ConvertUtil;

@Controller
public class EmpController {

	@Autowired
	EmpService empService;
	
	@Autowired
	DeptServiceInterface deptService;
	
	
	@RequestMapping("/emp/emplist.do")
	public String emplist(Model model) {
		model.addAttribute("emplist", empService.selectAll());
		return "emp/emplist";
	}
	
	@RequestMapping("/emp/empInsert.do")
	public String empinsert(Model model) {
		model.addAttribute("dlist", deptService.findAll()) ;
		model.addAttribute("mlist", deptService.findAllManager()) ;
		model.addAttribute("jlist", empService.selectAllJobs()) ;
		
		return "emp/empinsert"; //forward
	}
	@RequestMapping(value = "/emp/empInsert.do", 
			method = RequestMethod.POST)
	public String empinsertPost(EmpVO emp) {
		System.out.println(emp);
		empService.insertEmp(emp);
		return "redirect:/emp/emplist.do"; //재요청 
	}
	@RequestMapping("/emp/empDetail.do")
	public String empDetailGet(Model model, int empid) {
		model.addAttribute("emp", empService.selectById(empid)) ;
		model.addAttribute("dlist", deptService.findAll()) ;
		model.addAttribute("mlist", deptService.findAllManager()) ;
		model.addAttribute("jlist", empService.selectAllJobs()) ;

		return "emp/empdetail"; //forward
	}
	@RequestMapping(value = "/emp/empDetail.do", 
			method = RequestMethod.POST)
	public String empDetailPost( EmpVO emp) {
		System.out.println(emp);
		empService.updateEmp(emp);		
		return "redirect:/emp/emplist.do";  
	}
	@RequestMapping("/emp/empDelete.do")
	public String empDelete( int empid) {
		empService.deleteEmp(empid);		
		return "redirect:/emp/emplist.do";  
	}
	
	//http://localhost:9090/springmvcproject/emp/login.do?id=100&email=SKING
	
	@RequestMapping(value = "/emp/login.do", produces = {"text/html;charset=utf-8"})
	@ResponseBody //browser에 응답문서가 간다. body에 출력
	public String empLogin(int id, String email) {
		EmpVO emp = empService.loginChk(id, email);
		String message = "<h1>존재하지않은 사용자입니다.</h1>";
		if(emp !=null) message = emp.toString();
		return message;
	}
	
	@RequestMapping("/login/loginForm.do")
	public String loginFormGet() {
		return "/login/loginForm";
	}
	@RequestMapping(value = "/login/login3.do", method = RequestMethod.POST)
	public String loginPost(int userid, String userpw, HttpSession session) {
		EmpVO emp = empService.loginChk(userid, userpw);
		System.out.println(emp);
		if(emp!=null) {
			session.setAttribute("loginemp", emp);
			return "redirect:/emp/emplist.do";
		}else {
			return "redirect:/login/loginForm.do";
		}
	}
	
	
	@RequestMapping("/emp/selectByDept.do")
	public String selectByDept(Model model, int deptid) {
		List<EmpVO> elist = empService.selectByDept(deptid);
		model.addAttribute("emplist", elist);
		
		return "emp/emplist";
	}
	
	@RequestMapping("/emp/selectBySalary.do")
	public String selectBySalary(Model model, int minsal, int maxsal) {
		List<EmpVO> elist = empService.selectBySalary(minsal, maxsal);
		model.addAttribute("emplist", elist);
		
		return "emp/emplist";
	}
	
	@RequestMapping("/emp/selectByDate.do")
	public String selectByDate(Model model, String sdate, String edate) {
		List<EmpVO> elist = empService.selectByDate(sdate, edate);
		model.addAttribute("emplist", elist);
		
		return "emp/emplist";
	}
	
	@RequestMapping("/emp/selectByDate2.do")
	public String selectByDate(Model model, 
			Date sdate, Date edate) {
		List<EmpVO> elist = empService.selectByDate2(sdate, edate);
		model.addAttribute("emplist", elist);
		
		return "emp/emplist";
	}
	
	@RequestMapping("/emp/selectByCondition.do")
	public String selectByCondition(Model model, 
			String deptid, String jobid, 
			String sal, String hdate, String chk) {
		System.out.println(chk);
		int dept = "".equals(deptid)?0:Integer.parseInt(deptid);
		int sal2 = "".equals(sal)?0:Integer.parseInt(sal);
		if(chk==null) hdate = null;
		Date hiredate = null;
		if(hdate!="" && hdate!=null) 
			hiredate = ConvertUtil.covertDate(hdate);
		List<EmpVO> elist = empService.selectByCondition(dept, jobid, sal2, hiredate);
		model.addAttribute("emplist", elist);
		
		return "emp/emplist";
	}
	
	@RequestMapping("/emp/selectBydeptmany.do")
	public String selectBydeptmany(Model model, Integer[] deptlist) {
		//System.out.println(Arrays.toString(deptlist));
		List<EmpVO> elist = empService.selectBydeptmany(Arrays.asList(deptlist));	
		model.addAttribute("emplist", elist);
		return "emp/emplist";
	}
			
			
	
}