package net.spring.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.business.DeptDAO;
import com.kosta.business.EmpDAO;
import com.kosta.model.UserVO;

@Controller
public class KostaController {
	
	
@Autowired
DeptDAO deptDAO;

@Autowired
EmpDAO empDAO;
	
	
	@RequestMapping(value = {"/sample1.do", "/test/sample2.do"})
	public ModelAndView test1() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title","맵핑주소 여러개 사용가능");
		//페이지이름이 없다면 default로 요청주소 이름이 사용된다.
		//sample1.do ==> WEB-INF/views/samlpe1.jsp
		//test/sample2.do ==> WEB-INF/views/test/samlpe2.jsp
		return mv;
	}
	/*
	@RequestMapping(value = "sample3.do", params = {"userid=abc", "userpass","!email"})
	public ModelAndView test2() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title","맵핑주소 param 사용가능");
		return mv;
	}
	*/
	@RequestMapping(value = "sample3.do")
	public ModelAndView test2(@RequestParam("userid") String id, 
								String userpw, String email, HttpServletRequest request,
								Map<String , Object> userinfo,
								UserVO user
			) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title","파라미터 받기");
		mv.addObject("id", id);
		mv.addObject("userpass", userpw);
		mv.addObject("email", email);
		String email2 = request.getParameter("email");
		mv.addObject("email2", email2);
		mv.addObject("userinfo", userinfo);
		mv.addObject("user", user);
		mv.setViewName("sample3Result");
		return mv;
	}
	
	//리턴이 = Striong  , 페이지 이름이 리턴 해당페이지로 포워드
	//Model 페이지에 전달
	@RequestMapping(value = "sample4.do")
	public String test4(Model model, HttpSession session) {
		model.addAttribute("title", "page return");
		UserVO user =  new UserVO();
		user.setEmail("request에저장");
		model.addAttribute("user", user);
		
		UserVO user2 =  new UserVO();
		user2.setEmail("session에저장");
		session.setAttribute("user", user2);
		
		return "sample3Result";
	}
	@RequestMapping(value = "sample5.do")
	public void test5(HttpServletRequest request, HttpSession session) {
		String cpath = request.getServletPath();
		System.out.println(request.getMethod());
		System.out.println(request.getRealPath(cpath));
		System.out.println(request.getRequestURL());
		System.out.println(session.getServletContext().getRealPath(cpath));
	}
	
	@RequestMapping(value = "sample6.do")
	@ResponseBody
	public String test6() {
		return "!!";
	}
	@RequestMapping("emp/allmanager.do")
	public String deptallM(Model model ){
	
		model.addAttribute("mlist", deptDAO.findAllManager());
		return "emp/allmanager";
	}
	@RequestMapping("emp/empByDept.do")
	public String empByDept(Model model, int deptid ){
	
		model.addAttribute("emplist", empDAO.selectByDept(deptid));
		return "emp/emplist";
	}
	
	@RequestMapping("emp/alldeptlist.do")
	public String alldeptlist(Model model){
	
		model.addAttribute("dlist", deptDAO.findAll());
		return "emp/alldept";
	}
	@RequestMapping("emp/alljoblist.do")
	public String alljoblist(Model model){
	
		model.addAttribute("jlist", empDAO.selectAll());
		return "emp/alljoblist";
	}
	@RequestMapping("emp/empByJob.do")
	public String empByJob(Model model, String jobid){
	
		model.addAttribute("elist", empDAO.selectByJob(jobid));
		return "emp/emplist";
	}
	
}
