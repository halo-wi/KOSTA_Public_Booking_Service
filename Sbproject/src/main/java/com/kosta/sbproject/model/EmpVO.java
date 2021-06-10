package com.kosta.sbproject.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity //JPA관리함을 의미
@Table(name = "tbl_emp")
public class EmpVO {
	
	@Id
	@Column(name="employee_id")
	int employeeId;
	@Column(name="first_name")
	String firstName;
	@Column(name="last_name")
	String lastName;
	String email;
	@Column(name="phone_number")
	String phoneNumber;
	@Column(name="hire_date")
	Date hireDate;
	@Column(name="job_id")
	String jobId;
	@Column(name="salary",nullable = true)
	int salary;
	@Column(name="commission_pct")
	double commissionPct;
	@Column(name="manager_id")
	int managerId;
	
	@ManyToOne
	DeptVO dept; //dept_departmentId
}
