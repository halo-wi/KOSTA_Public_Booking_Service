package com.kosta.sbproject.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "board")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="bno")
@Table(name="tbl_freeboards")
public class FreeBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long bno;
	String title;
	String writer;
	String content;
	
	@CreationTimestamp
	private Timestamp regdate; //LocalDateTime
	@UpdateTimestamp
	private Timestamp updatedate; //LocalDateTime
	
	@JsonIgnore
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL) //, fetch = FetchType.EAGER
	List<FreeBoardReply> replies;
	
}