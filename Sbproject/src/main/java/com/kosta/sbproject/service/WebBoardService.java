package com.kosta.sbproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.sbproject.model2.PageVO;
import com.kosta.sbproject.model2.WebBoard;
import com.kosta.sbproject.persistence2.WebBoardRepositoy;
import com.querydsl.core.types.Predicate;

@Service
public class WebBoardService {

	@Autowired
	WebBoardRepositoy repo;
	
	public Page<WebBoard> selectAll(PageVO pvo) {
		Predicate p = repo.makePredicate(pvo.getType(),pvo.getKeyword());
		
		Pageable pageable = pvo.makePaging(0, "bno");
		Page<WebBoard> result = repo.findAll(p, pageable);
		return result;
	}
	
	public WebBoard selectById(Long bno) {
		return repo.findById(bno).get();
	}
	
	public WebBoard insertBoard(WebBoard board) {
		return repo.save(board);
	}
	
	public WebBoard updateBoard(WebBoard board) {
		return repo.save(board);
	}
	
	public int deleteBoard(Long bno) {
		int ret=0;
		try {
		repo.deleteById(bno);
		ret=1;
		}catch(Exception ex){
		}
		return ret;
	}
	
	
}
