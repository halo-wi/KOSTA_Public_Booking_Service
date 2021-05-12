package com.kosta.business;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;

public interface EmpService {
	
	public EmpVO loginChk(int empid, String email);
	public List<JobVO> selectAllJobs();
	
	public int deleteEmp(int empid);

	public int updateEmp(EmpVO emp) ;
	
	//����ڰ� ���� ���ؼ� ȸ������ =>controller=>DAO=>DB
	public int insertEmp(EmpVO emp) ;
	public List<EmpVO> selectAll() ;
	// 2.�⺻Ű(Primary key)..null�Ұ�, �ʼ�Į��,�ߺ�����
	// ������ȣ�� ��ȸ
	public EmpVO selectById(int empid) ;

	// 3.�μ��� ��ȸ
	public List<EmpVO> selectByDept(int deptid);

	// 4.job_id�� ��ȸ
	public List<EmpVO> selectByJob(String jobid) ;
	// 5.�޿��� ��ȸ
	public List<EmpVO> selectBySalary(int minsal, int maxsal) ;

	// 6.�Ի�����ȸ
	public List<EmpVO> selectByDate(String sdate, String edate) ;
	// 6.�Ի�����ȸ
	public List<EmpVO> selectByDate2(Date sdate, Date edate) ;

	// 7.�̸��� Ư�����ڰ� �� ��� ��ȸ
	public List<EmpVO> selectByName(String ch) ;
	
	// 8.����������ȸ(�μ�,job,salary,hire_date)
	public List<EmpVO> selectByCondition(int deptid, 
			                 String jobid, int sal,Date hdate) ;

	EmpVO makeEmp(ResultSet rs);
}
