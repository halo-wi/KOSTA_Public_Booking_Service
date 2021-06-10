package com.kosta.sbproject;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.kosta.sbproject.model.Board;
import com.kosta.sbproject.model.QBoard;
import com.kosta.sbproject.persistence.BoardRepository;
import com.querydsl.core.BooleanBuilder;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class BoardTest3_Query {

	@Autowired
	BoardRepository repo;
	
	//@Test
	public void test1() {
		repo.findByTitle2("2").forEach(b->{
			log.info(b.toString());
		});
	}
	
	//@Test
	public void test2() {
		repo.findByTitleAndBno("2", 30L).forEach(b->{
			log.info(b.toString());
		});
	}
	
	//@Test
	public void test3() {
		repo.findByContent("2", 100L).forEach(b->{
			log.info(b.toString());
		});
	}
	
	//@Test
	public void test4() {
		repo.findByWriter2("2").forEach(b->{
			log.info(b.toString());
		});
	}
	
	//@Test
	public void test5() {
		repo.findByWriter3("작성자 1").forEach(b->{
			log.info(b.toString());
		});
	}
	
	//@Test
	public void test6() {
		repo.findByWriter4("작성자 1").forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}

	//@Test
	public void test7() {
		repo.selectAllEmp().forEach(arr->{
			log.info(Arrays.toString(arr));
			System.out.println("직원이름:" + arr[1]);
		});
	}
	
	//@Test
	public void test8() {
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> result = repo.findByWriterPaging("8", paging);
		List<Board> blist = result.getContent();
		for(Board b:blist) {
			System.out.println(b);
		}
	}
	
	@Test
	public void test9() {
		String type = "content";
		String keyword = "1";
		BooleanBuilder builder = new BooleanBuilder();
		QBoard board = QBoard.board;
		if(type.equals("title")) {
			builder.and(board.title.like("%"+keyword+"%"));
		} else if(type.equals("content")) {
			builder.and(board.content.like("%"+keyword+"%"));
		} else if(type.equals("writer")) {
			builder.and(board.writer.like("%"+keyword+"%"));
		}
		builder.and(board.bno.gt(100L));
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> result = repo.findAll(builder, paging);
		List<Board> blist = result.getContent();
		blist.forEach(b->{
			System.out.println(b);
		});
	}
}
