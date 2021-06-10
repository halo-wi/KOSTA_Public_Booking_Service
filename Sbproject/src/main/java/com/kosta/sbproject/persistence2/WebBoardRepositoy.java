package com.kosta.sbproject.persistence2;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.sbproject.model2.QWebBoard;
import com.kosta.sbproject.model2.WebBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

// 상수+추상메서드들
// static method, default method
public interface WebBoardRepositoy extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard>{
//	//JPQL문법
	@Query("select b.title, count(r) "
			+ "from WebBoard b left outer join b.replies r "
			+ "group by b.title order by b.title")
	public List<Object[]> getBoardReplyCount();
	
	public default Predicate makePredicate1() {
		BooleanBuilder builder = new BooleanBuilder();
		QWebBoard board = QWebBoard.webBoard;
		builder.and(board.bno.gt(0)); // and bno>0
		
		return builder;
	}
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QWebBoard board = QWebBoard.webBoard;
		builder.and(board.bno.gt(0)); // and bno>0
		
		if(type==null) return builder;
		switch(type) {
		case "title":
			builder.and(board.title.like("%"+keyword+"%"));
			break;
		case "content":
			builder.and(board.content.like("%"+keyword+"%"));
			break;
		case "writer":
			builder.and(board.writer.like("%"+keyword+"%"));
			break;
		default:
			break;
		}
		
		return builder;
	}
}
