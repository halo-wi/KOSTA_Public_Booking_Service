package com.kosta.sbproject;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.kosta.sbproject.model.Board;
import com.kosta.sbproject.persistence.BoardRepository;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class BoardTest2 {
	
	@Autowired
	BoardRepository repo;
	
	//@Test
	public void test1() {
		//writer가 "writer1"인 데이터를 검색하기
		repo.findByWriter("작성자 1").forEach(data->{
			log.info(data.toString());
		});
	}

	//@Test
	public void test2() {
		//writer가 "writer1"인 데이터를 검색하기
		repo.findByTitle("board title 6").forEach(data->{
			log.info(data.toString());
		});
	}

	//@Test
	public void test3() {
		//writer가 "writer1"인 데이터를 검색하기
		repo.findByContent("board content 5").forEach(data->{
			log.info(data.toString());
		});
	}
	
	//@Test
	public void test4() {
		// where Title like '%title 2%'
		repo.findByTitleContaining("title 2").forEach(data -> {
			log.info(data.toString());
		});
	}
	//@Test
	public void test5() {
		// where Title like 'board%'
		repo.findByTitleStartingWith("board").forEach(data -> {
			log.info(data.toString());
		});
	}
	//@Test
	public void test6() {
		// where Title like '%title 2'
		repo.findByTitleEndingWith("title 2").forEach(data -> {
			log.info(data.toString());
		});
	}
	
	//@Test
	public void test7() {
		// where Title like '%2' and bno > 41
		repo.findByTitleEndingWithAndBnoGreaterThan("2", 41L).forEach(data -> {
			log.info(data.toString());
		});
	}
	
	//@Test
	public void test8() {
		// where bno > 41 order by bno desc
		repo.findByTitleEndingWithAndBnoGreaterThanOrderByBnoDesc("2", 41L).forEach(data -> {
			log.info(data.toString());
		});
	}
	//@Test
	public void test9() {
		Timestamp regdate = new Timestamp(System.currentTimeMillis());
		repo.findByRegdateAfter(regdate).forEach(data -> {
			log.info(data.toString());
		});
	}

	//@Test
	public void test10() {
		System.out.println("----------1page-----------");
		Pageable paging = PageRequest.of(0, 5);
		List<Board> boardlist = repo.findByTitleEndingWithAndBnoGreaterThan("2",10L, paging);
		
		boardlist.forEach(b->{
			System.out.println(b);
		});
		System.out.println("----------2page-----------");
		Pageable paging2 = PageRequest.of(1, 5);
		List<Board> boardlist2 = repo.findByBnoGreaterThanOrderByBnoDesc(10L, paging2);
		
		boardlist2.forEach(b->{
			System.out.println(b);
		});
		System.out.println("----------3page-----------");
		Pageable paging3 = PageRequest.of(2, 5, Direction.ASC, "bno");
		List<Board> boardlist3 = repo.findByBnoGreaterThanOrderByBnoDesc(10L, paging3);
		
		boardlist3.forEach(b->{
			System.out.println(b);
		});
	}
	
	//@Test
	public void boardPagingTest() {
		System.out.println("----------0page-----------");
		Pageable paging = PageRequest.of(0, 3);
		Page<Board> result = repo.findByTitleContaining("2", paging);
		
		System.out.println("getSize:"+result.getSize());
		System.out.println("getTotalPages:"+result.getTotalPages());
		System.out.println("getTotalElements:"+result.getTotalElements());
		System.out.println("nextPageable:"+result.nextPageable());
		System.out.println("previousPageable:"+result.previousPageable());
		
		List<Board> boardlist = result.getContent();
		boardlist.forEach(b->{
			System.out.println(b);
		});
	}
	
	@Test
	public void boardPagingTest2() {
		System.out.println("----------0page-----------");
		Pageable paging = PageRequest.of(0, 3);
		Pageable paging2 = PageRequest.of(0, 3, Direction.DESC,"bno");
		Pageable paging3 = PageRequest.of(0, 3, Sort.by("bno"));
		Page<Board> result = repo.findAll(paging3);
		
		List<Board> boardlist = result.getContent();
		boardlist.forEach(b->{
			System.out.println(b);
		});
	}
	
	//@Test //여러건 insert
	public void insertBoard2() {
		IntStream.range(1,101).forEach(i->{     //1부터 200번까지 돌아라
			Board b = new Board();
			b.setTitle("board title "+i);
			b.setContent("board content "+i);
			b.setWriter("작성자 "+i%10);
			repo.save(b);
		});
	}
}
