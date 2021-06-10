package com.kosta.sbproject.model2;

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
@ToString(exclude = "board")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(of="rno")
@Table(name="tbl_web_replies")
public class WebBoardReply {
   
   
   
   @Id //필수PK
   @GeneratedValue(strategy = GenerationType.AUTO)
   Long rno;
   String reply;
   String replyer;
   
   @CreationTimestamp
   Timestamp regdate;
   
   @UpdateTimestamp
   Timestamp updatedate;
   
   //여러개의 댓글은 하나의 게시글을 참조한다.
   @ManyToOne
   WebBoard board; //FK 
   //tbl_free_replies테이블에 board_bno칼럼추가
}
