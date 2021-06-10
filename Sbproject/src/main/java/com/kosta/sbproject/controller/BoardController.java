package com.kosta.sbproject.controller;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.sbproject.model.FreeBoard;
import com.kosta.sbproject.model.MemberDTO;
import com.kosta.sbproject.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService bservice;
	
	@GetMapping("/freeboard/boardDetail")
	public void selectBoard(Model model, Long bno) {
		model.addAttribute("board", bservice.selectById(bno));
		model.addAttribute("boardlist", bservice.selectAll());
	}

	@GetMapping("/freeboard/boardList")
	public String selectAll(Model model) {
		model.addAttribute("boardlist", bservice.selectAll());
		return "/freeboard/boardlist";
	}
	
	@GetMapping("/expression")
	public void expression(Model model, HttpSession session) {
		model.addAttribute("boardlist", bservice.selectAll());
		MemberDTO member = MemberDTO.builder()
				.mname("Nam")
				.mpassword("1234")
				.build();
		session.setAttribute("user", member);
	}
	
	@GetMapping("/expression2")
	public void expression2(Model model, HttpSession session) {
		model.addAttribute("now", new Date());
		model.addAttribute("price", 123456789);
		model.addAttribute("title", "This is just sample");
		model.addAttribute("options", Arrays.asList("apple","banana","orange"));
		
	}
	
	@RequestMapping("/hoosss1")
	public void test1() {
		// default로 페이지에 연결된다. templates/test1.html
	}

	@RequestMapping(value="/hoosss2", method = RequestMethod.GET)
	public String test2() {
		return "hoosss1";
	}

	@GetMapping("/hoosss3")
	public String test3(Model model) {
		
		FreeBoard board = FreeBoard.builder()
				.bno(100L)
				.title("Thymeleaf")
				.content("오늘은 비가옵니다.")
				.build();
		
		model.addAttribute("greeting", "안녕하세요!");
		model.addAttribute("board",board);
		return "hoosss1";
	}
}
