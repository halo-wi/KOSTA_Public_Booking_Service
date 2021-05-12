package com.kosta.business;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;
@Repository("empservice")
public class EmpServiceImpl implements EmpService{
	
	@Autowired
	@Qualifier("empDAOMybatis")
	EmpDAOInterface empDAO;
	
	@Override
	public EmpVO loginChk(int empid, String email) {
		// TODO Auto-generated method stub
		return empDAO.loginChk(empid, email);
	}

	@Override
	public List<JobVO> selectAllJobs() {
		// TODO Auto-generated method stub
		return empDAO.selectAllJobs();
	}

	@Override
	public int deleteEmp(int empid) {
		// TODO Auto-generated method stub
		return empDAO.deleteEmp(empid);
	}

	@Override
	public int updateEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		return empDAO.updateEmp(emp);
	}

	@Override
	public int insertEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		return empDAO.insertEmp(emp);
	}

	@Override
	public List<EmpVO> selectAll() {
		// TODO Auto-generated method stub
		return empDAO.selectAll();
	}

	@Override
	public EmpVO selectById(int empid) {
		// TODO Auto-generated method stub
		return empDAO.selectById(empid);
	}

	@Override
	public List<EmpVO> selectByDept(int deptid) {
		// TODO Auto-generated method stub
		return empDAO.selectByDept(deptid);
	}

	@Override
	public List<EmpVO> selectByJob(String jobid) {
		// TODO Auto-generated method stub
		return empDAO.selectByJob(jobid);
	}

	@Override
	public List<EmpVO> selectBySalary(int minsal, int maxsal) {
		// TODO Auto-generated method stub
		return empDAO.selectBySalary(minsal, maxsal);
	}

	@Override
	public List<EmpVO> selectByDate(String sdate, String edate) {
		// TODO Auto-generated method stub
		return empDAO.selectByDate(sdate, edate);
	}

	@Override
	public List<EmpVO> selectByDate2(Date sdate, Date edate) {
		// TODO Auto-generated method stub
		return empDAO.selectByDate2(null, null);
	}

	@Override
	public List<EmpVO> selectByName(String ch) {
		// TODO Auto-generated method stub
		return empDAO.selectByName(ch);
	}

	@Override
	public List<EmpVO> selectByCondition(int deptid, String jobid, int sal, Date hdate) {
		// TODO Auto-generated method stub
		return empDAO.selectByCondition(deptid, jobid, sal, null);
	}

	@Override
	public EmpVO makeEmp(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
