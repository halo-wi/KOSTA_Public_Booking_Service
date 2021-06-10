package com.kosta.sbproject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="pid")
@Table(name="tbl_pdsboard")

public class PDSBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long pid;
	String pname;
	String pwriter;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// FetchType.EAGER :즉시실행
	// FetchType.LAZY : 지연실행 (default)
	@JoinColumn(name="pdsno")
	List<PDSFile> file2;
	
	
	/*
	@OneToMany 기본적으로 중간테이블이 생김
	
	@JoinColumn(name="pdsno")추가
	tbl_pdsfiles테이블에 pdsno칼럼이 생긴다.
	tbl_pdsboard테이블은 칼럼이 추가되지않는다.
	? 다치불가하다. (한ROW에 한 칼럼에 값 여러개 불가)
	
	영속전이
	부모 또는 자식이 변경되면 연관관계 엔티티가 모두 영향을 준다.
	cascade = CascadeType.ALL
	*/
}
