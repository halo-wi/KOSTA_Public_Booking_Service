package com.kosta.sbproject.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="tbl_boards")
public class Board {
	
	@Id // PK필수
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long bno;
	
	
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp
	private Timestamp regdate; //LocalDateTime
	@UpdateTimestamp
	private Timestamp updatedate; //LocalDateTime
	
}

//변수이름에 대문자가 나오면 테이블 칼럼이름에 _가 들어간다.
//Repository에서 변수이름 사용시 주의 (findBy대문자시작 그리고 변수이름 그대로 : findByRegDate)