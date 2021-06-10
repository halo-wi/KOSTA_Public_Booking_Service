package com.kosta.sbproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.sbproject.model2.PageMaker;
import com.kosta.sbproject.model2.PageVO;
import com.kosta.sbproject.model2.WebBoard;
import com.kosta.sbproject.service.WebBoardService;

@Controller
public class WebBoardController {

	@Autowired
	WebBoardService service;
	
	@GetMapping("/webboard/boardlist")
	public void selectAll(Model model, PageVO pagevo) {
		Page<WebBoard> result = service.selectAll(pagevo);
		
		List<WebBoard> boardlist = result.getContent();
		System.out.println(1);
		boardlist.forEach(b->{
			System.out.println(2);
			System.out.println("asdfsadfsdafajsdflk"+b);
		});
		System.out.println("한페이지의 사이즈"+result.getSize());
		System.out.println("전체페이지"+result.getTotalPages());
		
		model.addAttribute("boardResult", result);
		model.addAttribute("pagevo", pagevo);
		model.addAttribute("result", new PageMaker<>(result));
		
	}
	
	@GetMapping("/webboard/register")
	public void boardRegister() {
		
	}
	

	@PostMapping("/webboard/register")
	public String boardRegisterPost(WebBoard board, RedirectAttributes rttr) {
		System.out.println(board);
		
		WebBoard ins_board = service.insertBoard(board);
		//주소창에 보이지 않고 전달된다.
		rttr.addFlashAttribute("resultMessage", ins_board==null?"입력실패":"입력성공");
		return "redirect:/webboard/boardlist";
	}
	
	
	  @GetMapping("/webboard/boarddetail") 
	  public void selectAll(Model model, Long bno, PageVO pagevo) { 
		  model.addAttribute("board",service.selectById(bno)); 
		  model.addAttribute("pagevo", pagevo);
		  }
	  
	  
	  
	  @GetMapping("/webboard/delete")
	  public String boardDelete(Long bno) {
		  int ret = service.deleteBoard(bno);
		  System.out.println("삭제 : "+ret);
		  return "redirect:/webboard/boardlist";
				 
	  }
	  

	  
	  
	  
	  
	  @PostMapping("/webboard/update")
	  public String boardUpdate(WebBoard board, RedirectAttributes rttr, Integer page, Integer size,
			  String type, String keyword) {
		  WebBoard update_board= service.updateBoard(board);
		  System.out.println("수정사항 : "+update_board);
		  
		  rttr.addFlashAttribute("resultMessage", update_board==null?"수정실패":"수정성공");
		  
		  
		  String param = "page=" + page + "&size=" + size + "&type="+type+"&keyword="+keyword;
		  return "redirect:/webboard/boardlist?" + param;
				 
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	 
}
