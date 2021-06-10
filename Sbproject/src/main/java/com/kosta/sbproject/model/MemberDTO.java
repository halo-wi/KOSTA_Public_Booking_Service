package com.kosta.sbproject.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"mid"})
@Entity
@Table(name="tbl_members")
public class MemberDTO { //security에서 인증으로 사용하고자 한다
	@Id
	String mid;
	String mname;
	String mpassword;//security를 위해 반드시 암호화되어야한다.
	@Enumerated(EnumType.STRING)
	MemberRoleEnumType mrole; //인가를 위해 필요하다. mrole에 따라 권한 부여, 권한에 따라 접근가능여부를 결정한다.
}
