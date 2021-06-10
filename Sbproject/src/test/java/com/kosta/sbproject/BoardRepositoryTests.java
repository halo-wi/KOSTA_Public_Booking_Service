package com.kosta.sbproject;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kosta.sbproject.model.Board;
import com.kosta.sbproject.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {
	
	@Autowired
	private BoardRepository boardRepo; 
	
	//@Test
	public void testInsert() {
		Board board = new Board();
		board.setTitle("게시물 제목2");
		board.setContent("게시물 내용 넣기2 ....");
		board.setWriter("user02");
		
		boardRepo.save(board);
	}
	
	//@Test
	public void testRead() {
		boardRepo.findById(2L).ifPresent((board)-> {
			System.out.println(board);
		});
	}
	
	@Test
	public void testRead2() {
		for(Board board:boardRepo.findAll()) {
			System.out.println(board);			
		}
	}
}
