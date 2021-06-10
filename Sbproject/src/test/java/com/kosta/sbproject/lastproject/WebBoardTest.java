package com.kosta.sbproject.lastproject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import com.kosta.sbproject.model2.PageVO;
import com.kosta.sbproject.model2.WebBoard;
import com.kosta.sbproject.model2.WebBoardReply;
import com.kosta.sbproject.persistence2.WebBoardRepositoy;
import com.querydsl.core.types.Predicate;

import lombok.extern.java.Log;

@Log
@Commit
@SpringBootTest
public class WebBoardTest {
	
	@Autowired
	WebBoardRepositoy repo;
	
	//@Test
	public void conditionRetrieve() {
		Predicate p = repo.makePredicate(null, null);
		//Pageable pageable = PageRequest.of(0, 3);
		PageVO pvo = new PageVO();
		Pageable pageable = pvo.makePaging(0, "bno");
		Page<WebBoard> result = repo.findAll(p, pageable);
		List<WebBoard> boardlist = result.getContent();
		System.out.println("여긴오");
		boardlist.forEach(b->{
			System.out.println(b);
		});
		System.out.println("한페이지의 사이즈"+result.getSize());
		System.out.println("전체페이지"+result.getTotalPages());
	}
	//@Test
//	public void conditionRetrieve1() {
//		Predicate p = repo.makePredicate();
//		Pageable pageable = PageRequest.of(0, 3);
//		
//		Page<WebBoard> result = repo.findAll(p, pageable);
//		List<WebBoard> boardlist = result.getContent();
//		
//		boardlist.forEach(b->{
//			System.out.println(b);
//		});
//		System.out.println("한페이지의 사이즈"+result.getSize());
//		System.out.println("전체페이지"+result.getTotalPages());
//	}
	
	//@Test
	public void boardReplyCount2() {
		repo.getBoardReplyCount().forEach(arr->{
			System.out.println(Arrays.toString(arr));
		});
	}
	
	@Transactional
	//@Test
	public void boardReplyCount() {
		repo.findById(690L).ifPresent(b->{
			System.out.println(b.getReplies().size());
		});
	}
	
	@Transactional
	@Test
	public void insertReply() {
		repo.findById(690L).ifPresent(b->{
			List<WebBoardReply> replies = b.getReplies();
			b.setTitle("title 수정합니다1.");
			IntStream.range(1, 4).forEach(i->{
				WebBoardReply wreply = WebBoardReply.builder()
						.reply("댓글..."+i)
						.replyer("작성자"+i)
						.board(b)
						.build();
				replies.add(wreply);
			});
			repo.save(b);
		});
	}
	
	
	//@Test
	public void insertBoardReply() {
		IntStream.range(101, 501).forEach(i->{
			WebBoard board = WebBoard.builder()
					.title("토요일....."+i)
					.content("해가쨍쨍"+i)
					.writer("작성자"+i%3)
					.build();
			repo.save(board);
		});
	}
	
	
}
