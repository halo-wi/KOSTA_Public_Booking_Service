package com.kosta.sbproject.persistence2;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.sbproject.model2.WebBoard;
import com.kosta.sbproject.model2.WebBoardReply;

public interface WebBoardReplyRepository extends CrudRepository<WebBoardReply, Long>{
	public List<WebBoardReply> findByBoard(WebBoard board);
}
