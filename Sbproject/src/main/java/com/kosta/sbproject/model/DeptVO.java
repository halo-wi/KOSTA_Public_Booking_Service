package com.kosta.sbproject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "emplist")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity //JPA관리함을 의미
@Table(name = "tbl_dept",
uniqueConstraints = {@UniqueConstraint(name = "unique2", columnNames = {"manager_id","location_id"})})
public class DeptVO {
	//자동증가시에 필요, 정책이 DB마다 다르다. AUTO(SEQUENCE, IDENTITY)
	//hibernate_sequence가 생성되어 사용된다. insert시에 hibernate_sequence.nextval 사용
	//IDENTITY:ATO_increment
	@Id //필수
	@GeneratedValue(strategy = GenerationType.AUTO)
	int departmentId;
	
	@Column(name = "dept_name", unique = true, nullable = false, length = 30)
	String departmentName;
	
	@Column(name = "manager_id", nullable = true)
	int managerId;
	
	@Column(name = "location_id", nullable = true)
	int locationId;
	
	//하나의 부서에는 여러명의 직원이있다.
	@OneToMany(mappedBy = "dept",
				cascade = CascadeType.ALL,
				fetch = FetchType.EAGER) 
		//default:LAZY(부서정보 얻을때 직원정보는 수행하지 않음)
		//EAGER: 부서정보 얻을때 직원정보도 select
	List<EmpVO> emplist;
	
}
