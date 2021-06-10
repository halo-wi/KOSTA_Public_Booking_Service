package com.kosta.sbproject.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "replies")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="rno")
@Table(name="tbl_free_replies")
public class FreeBoardReply {
	@Id // PK필수
	@GeneratedValue(strategy = GenerationType.AUTO) 
	Long rno;
	String reply;
	String replyer;
	
	
	@CreationTimestamp
	private Timestamp regdate; //LocalDateTime
	@UpdateTimestamp
	private Timestamp updatedate; //LocalDateTime
	
	// 여러개의 댓글은 하나의 게시글을 참조한다.
	@ManyToOne
	FreeBoard board; // FK
}
