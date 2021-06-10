package com.kosta.sbproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.kosta.sbproject.model.PDSBoard;
import com.kosta.sbproject.model.PDSFile;
import com.kosta.sbproject.persistence.PDSBoadRepository;
import com.kosta.sbproject.persistence.PDSFileRepository;

import lombok.extern.java.Log;

@Commit //@Transactional이 rollback되지 않도록하기
@Log
@SpringBootTest
public class PDSBoardTest {
	
	@Autowired
	PDSBoadRepository boardRepo;
	@Autowired
	PDSFileRepository fileRepo;
	
	
	@Test
	public void test6() {
		boardRepo.findById(211L).ifPresent(b->{
			b.setPname("파일이름수정함2");
			b.setPwriter("후승남");
			List<PDSFile> flist = b.getFile2();
			PDSFile f = PDSFile.builder().pdsfile("board로 file추가").build();
			flist.add(f);
			boardRepo.save(b);
		});
	}
	
	//@Test
	public void test5() {
		fileRepo.findById(208L).ifPresent(f->{
			f.setPdsfile("파일이름수정함2");
			fileRepo.save(f);
		});
	}
	
	//@Transactional //@Query가 DML인 경우 (insert, delete, update),자동 rollback;
	//@Test
	public void test4() {
		int result = boardRepo.updatePdsFile2(206L,"파일이름수정함");
		System.out.println(result + "건 수정함!");
	}
	
	//@Test
	public void test3() {
		boardRepo.getBoardWithFileCount3().forEach(arr->{
			System.out.println(Arrays.toString(arr));
		});
	}

	//@Test
	public void test2() {
		boardRepo.findById(207L).ifPresent(b->{
			System.out.println(b.getPname());
			System.out.println(b.getPwriter());
			System.out.println(b); // FetchType.LAZY 실패
			// FetchType.EAGER : 즉시실행
			System.out.println(b.getFile2().size());
		});
	}
	
	//@Test
	public void test1() {
		//pdsboard로 pdsboard, pdsfile entity를 insert
		IntStream.range(1, 6).forEach(i->{
			PDSBoard board = new PDSBoard();
			board.setPname("게시글"+i);			
			board.setPwriter("hoos"+i);
			
			
			List<PDSFile> filelist = new ArrayList<>();
			IntStream.range(1, 4).forEach(j->{
				PDSFile file = new PDSFile();
				file.setPdsfile("hoosss파일" + j);
				filelist.add(file);
			});
			board.setFile2(filelist);
			boardRepo.save(board);
		});
	}
}
