package com.kosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.business.DeptServiceImpl;
import com.kosta.model.DeptVO;

@Controller
public class DeptController {
   
   
   //보통 DAO를 Service에서 호출하지 아래처럼 호출하지는 않는다.
     //보통 DAO도 인터페이스로 받아서 사용한다.
   @Autowired //DeptDAO deptDAO=new DeptDAO();
   DeptServiceImpl service;
   
   @RequestMapping("/dept/deptlist.do")
   public String deptlist(Model model) {
      model.addAttribute("deptall",service.findAll());
      return "dept/dept_retrieve";
   }
   
   @RequestMapping("/dept/deptDetail.do")
   public String deptDetailGet(Model model,int deptid) {
      model.addAttribute("dept",service.findById(deptid));
      model.addAttribute("mlist",service.findAllManager());
      model.addAttribute("loclist",service.findAllLocation());
      return "dept/deptDetail";
   }
   
   @RequestMapping(value = "/dept/deptDetail.do", method=RequestMethod.POST)
   public String deptDetailPost(DeptVO dept) {
      service.update(dept);
      return "redirect:/dept/deptlist.do";
   }
   
   @RequestMapping("/dept/deptDelete.do")
   public String deptDelete(int deptid) {
      service.delete(deptid);
      return "redirect:/dept/deptlist.do";
   }
   
   @RequestMapping("/dept/deptInsert.do")
   public String deptInsertGet(Model model) {
      model.addAttribute("mlist",service.findAllManager());
      model.addAttribute("loclist",service.findAllLocation());
      return "dept/deptInsert";
   }
   
   @RequestMapping(value = "/dept/deptInsert.do", method=RequestMethod.POST)
   public String deptInsertPost(DeptVO dept) {
      service.insert(dept);
      return "redirect:/dept/deptlist.do";
   }
   
   @RequestMapping(value = "/dept/transactionTest.do")
   public String insertUpdate() {
	   DeptVO new_dept = new DeptVO(1, "t1", 100, 1700);
	   DeptVO update_dept = new DeptVO(10, "t2", 100, 1700);
	   service.insertUpdate(new_dept, update_dept);
	   return "dept/transactionTest.do"; 
   }
   
   
}