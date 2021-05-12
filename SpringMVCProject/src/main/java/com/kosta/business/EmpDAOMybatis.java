package com.kosta.business;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;

@Repository
public class EmpDAOMybatis implements EmpDAOInterface{

	@Autowired
	SqlSession session;
	
	String namespace="com.gasan.emp.";
	
	@Override
	public EmpVO loginChk(int empid, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobVO> selectAllJobs() {
		List<JobVO> joblist = session.selectList(namespace+"selectAllJob");
		System.out.println(joblist.size() + "건의 job있다.");
		return joblist;
	}

	@Override
	public int deleteEmp(int empid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmp(EmpVO emp) {
		int result = session.update(namespace+"empUpdate", emp);
		System.out.println(result + "건 수정");
		return result;
	}

	@Override
	public int insertEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = session.selectList(namespace + "selectAll");
		System.out.println(emplist.size() + "건");
		return emplist;
	}

	@Override
	public EmpVO selectById(int empid) {
		EmpVO emp = session.selectOne(namespace+"selectById", empid);
		System.out.println("한건:" + emp);
		return emp;
	}

	@Override
	public List<EmpVO> selectByDept(int deptid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpVO> selectBySalary(int minsal, int maxsal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpVO> selectByDate(String sdate, String edate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpVO> selectByDate2(Date sdate, Date edate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpVO> selectByName(String ch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpVO> selectByCondition(int deptid, String jobid, int sal, Date hdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpVO> selectByJob(String jobid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpVO makeEmp(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
