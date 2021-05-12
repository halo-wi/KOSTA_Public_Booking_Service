package net.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.model.DeptVO;

@Controller
public class TestController {
	
	@RequestMapping("hello.do")
	public ModelAndView helloGet() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "hello.do 요청 by Get");
		mv.addObject("dept", new DeptVO(10, "de", 100 , 1007));
		mv.setViewName("test/helloForm");
		return mv;
	}
	@RequestMapping(value = "hello.do", method = RequestMethod.POST)
	public ModelAndView helloPost() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "hello.do 요청 by Post");
		mv.addObject("dept", new DeptVO(10, "de", 100 , 1007));
		mv.setViewName("test/helloResult");
		return mv;
	}
}
