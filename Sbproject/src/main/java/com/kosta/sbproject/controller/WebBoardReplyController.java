package com.kosta.sbproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.sbproject.model2.WebBoard;
import com.kosta.sbproject.model2.WebBoardReply;
import com.kosta.sbproject.service.WebBoardReplyService;

@RestController


@RequestMapping("/replies/*")
public class WebBoardReplyController {
	
	@Autowired
	WebBoardReplyService service;
	
	//특정 보드 번호에 해당하는 모든 댓글 조회
	@GetMapping("/board/{bno}")
	public ResponseEntity<List<WebBoardReply>> selectAll(@PathVariable Long bno) {
		WebBoard board = WebBoard.builder().bno(bno).build();
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	//댓글상세보기
		@GetMapping("{rno}")
		public ResponseEntity<WebBoardReply> viewReply(@PathVariable Long rno) {
				
				return new ResponseEntity<>(service.selectById(rno), HttpStatus.OK);
			}
	
	
	
	//특정 보드에 댓글등록, 입려 후 다시 조회
	@PostMapping("/{bno}")
	public ResponseEntity<List<WebBoardReply>> addRep(@PathVariable Long bno, @RequestBody WebBoardReply reply) {
		
		WebBoard board = WebBoard.builder().bno(bno).build();
		reply.setBoard(board);
		service.updateOrInsert(reply);
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.CREATED);
	}
	
		
	
	
	//삭제
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<WebBoardReply>> deleteByRno(@PathVariable Long rno, @PathVariable Long bno) {
		service.delete(rno);
		WebBoard board = WebBoard.builder().bno(bno).build();
		
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	//수정
	@PutMapping("/{bno}")
	public ResponseEntity<List<WebBoardReply>> updateRep(@PathVariable Long bno, @RequestBody WebBoardReply reply) {
		
		WebBoard board = WebBoard.builder().bno(bno).build();
		reply.setBoard(board);
		service.updateOrInsert(reply);
		
		return new ResponseEntity<>(service.selectAll(board), HttpStatus.OK);
	}
	
	

	
	
	
	
	
	
	
}
