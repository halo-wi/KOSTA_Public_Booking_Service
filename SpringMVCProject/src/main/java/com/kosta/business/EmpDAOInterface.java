package com.kosta.business;

import java.sql.Date;
import java.util.List;

import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;

public interface EmpDAOInterface {
	public EmpVO loginChk(int empid, String email); 
	public List<JobVO> selectAllJobs(); 
	public int deleteEmp(int empid) ;
	public int updateEmp(EmpVO emp) ;
	public int insertEmp(EmpVO emp); 
	public List<EmpVO> selectAll() ;
	public EmpVO selectById(int empid);
	public List<EmpVO> selectByDept(int deptid) ;
	public List<EmpVO> selectBySalary(int minsal, int maxsal) ;
	public List<EmpVO> selectByDate(String sdate, String edate); 
	public List<EmpVO> selectByDate2(Date sdate, Date edate) ;
	public List<EmpVO> selectByName(String ch);
	public List<EmpVO> selectByCondition(int deptid, String jobid, int sal,Date hdate);
	public List<EmpVO> selectBydeptmany(List<Integer> deptidList);
}
