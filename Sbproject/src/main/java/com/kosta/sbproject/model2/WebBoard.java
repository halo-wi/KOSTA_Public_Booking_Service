package com.kosta.sbproject.model2;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "replies")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="tbl_webboards")
public class WebBoard {
   
   @Id //필수PK
   @GeneratedValue(strategy = GenerationType.AUTO)
   Long bno;
   String title;
   String writer;
   String content;
   @CreationTimestamp
   Timestamp regdate;
   @UpdateTimestamp
   Timestamp updatedate;
   @JsonIgnore
   //tostring과 유사, JSON만들때 무한loop 방지
   @OneToMany(mappedBy = "board",
         cascade = CascadeType.ALL,
         fetch = FetchType.LAZY
         ) //,fetch = FetchType.EAGER
   List<WebBoardReply> replies;
}

//mappedBy는 메여있다. WebBoardReply의 board속성이 참조하고 있다,
//참조하고 있어서 자유롭지 못하다. 자식이 있으면 부모는 지울 수 없다.
// FetchType.EAGER : select시에 연관관계 table도 select한다.