package com.kosta.sbproject;

import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.kosta.sbproject.model.FreeBoard;
import com.kosta.sbproject.model.FreeBoardReply;
import com.kosta.sbproject.persistence.FreeBoardReplyRepository;
import com.kosta.sbproject.persistence.FreeBoardRepository;

import lombok.extern.java.Log;

@Commit
@Log
@SpringBootTest
public class FreeBoardTest {
	
	@Autowired
	FreeBoardRepository boardRepo;

	@Autowired
	FreeBoardReplyRepository replyRepo;
	
	//fetch = FetchType.LAZY(지연실행인 경우는 반드시 사용)
	@Transactional // fetch = FetchType.EAGER(즉시실행인 경우는 미사용)
	@Test
	public void test4() {
		boardRepo.findById(433L).ifPresent(b->{
			List<FreeBoardReply> rlist = b.getReplies();
			IntStream.range(1, 6).forEach(i->{
				FreeBoardReply reply = FreeBoardReply.builder()
						.reply("댓글작성 433내용")
						.replyer("후우")
						.board(b)
						.build();
				rlist.add(reply);
			});
			boardRepo.save(b);
		});
	}
	
	//@Test
	public void test3() {
		boardRepo.findAll().forEach(b->{
			System.out.println(b.toString());
		});
	}
	
	//@Test
	public void test2() {
		boardRepo.findAll().forEach(b->{
			System.out.println(b.toString());
		});
	}
	
	//@Test
	public void test1() {
		IntStream.range(1, 201).forEach(i->{
			FreeBoard board = FreeBoard.builder()
					.title("FreeBoard title" +i)
					.content("FreeBoard content" +i)
					.writer("writer"+i%10)
					.build();
			boardRepo.save(board);
		});
	}
}
