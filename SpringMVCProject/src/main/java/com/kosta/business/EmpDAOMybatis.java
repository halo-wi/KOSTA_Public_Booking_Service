package com.kosta.business;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, Object> empInfo = new HashMap<>();
		empInfo.put("empid", empid);
		empInfo.put("email", email);
		EmpVO emp = session.selectOne(namespace + "loginChk",empInfo );
		return emp;
	}

	@Override
	public List<JobVO> selectAllJobs() {
		List<JobVO> joblist = session.selectList(namespace+"selectAllJob");
		System.out.println(joblist.size() + "건의 job있다.");
		return joblist;
	}

	@Override
	public int deleteEmp(int empid) {
		int result = session.delete(namespace+"empDelete", empid);
		System.out.println(result + "건 삭제");
		return result;
	}

	@Override
	public int updateEmp(EmpVO emp) {
		int result = session.update(namespace+"empUpdate", emp);
		System.out.println(result + "건 수정");
		return result;
	}

	@Override
	public int insertEmp(EmpVO emp) {
		System.out.println("*" + emp.getPhone_number() +"*");
		//emp.setPhone_number(null);
		int result = session.insert(namespace + "empInsert", emp);
		System.out.println(result + "건 입력");
		return result;
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
		List<EmpVO> emplist = session.selectList(
				  namespace+"selectByDept", deptid);
		System.out.println("selectByDept:" + emplist.size() + "건");
		return emplist;
	}

	@Override
	public List<EmpVO> selectBySalary(int minsal, int maxsal) {
		Map<String,Integer> salMap = new HashMap<>();
		salMap.put("min", minsal);	
		salMap.put("max", maxsal);
		List<EmpVO> emplist = session.selectList(
				  namespace+"selectBySalary", salMap);
		System.out.println("selectBySalary:" + emplist.size() + "건");
		return emplist;
	}

	@Override
	public List<EmpVO> selectByDate(String sdate, String edate) {
		Map<String,String> dateMap = new HashMap<>();
		dateMap.put("sdate", sdate);	
		dateMap.put("edate", edate);
		List<EmpVO> emplist = session.selectList(
				  namespace+"selectByDate", dateMap);
		System.out.println("selectByDate:" + emplist.size() + "건");
		return emplist;
	}

	@Override
	public List<EmpVO> selectByDate2(Date sdate, Date edate) {
		Map<String,Date> dateMap = new HashMap<>();
		dateMap.put("sdate", sdate);	
		dateMap.put("edate", edate);
		List<EmpVO> emplist = session.selectList(
				  namespace+"selectByDate2", dateMap);
		System.out.println("selectByDate2:" + emplist.size() + "건");
		return emplist;
	}

	@Override
	public List<EmpVO> selectByName(String ch) {
		List<EmpVO> emplist = session.selectList(namespace + "selectByName", "%"+ch+"%");
		System.out.println(emplist.size() + "건");
		return emplist;
	}

	@Override
	public List<EmpVO> selectByCondition(int deptid, String jobid, 
			int sal, Date hdate) {
		Map<String,Object> map = new HashMap<>();
		map.put("deptid", deptid);	
		map.put("jobid", jobid);	
		map.put("sal", sal);	
		map.put("hdate", hdate);	
		List<EmpVO> emplist = session.selectList(namespace + "selectByCondition", map);
		System.out.println(emplist.size() + "건");
		return emplist;
	}

	@Override
	public List<EmpVO> selectBydeptmany(List<Integer> deptidList) {
		System.out.println(deptidList);
		List<EmpVO> emplist = session.selectList(namespace + "selectBydeptmany", deptidList);
		System.out.println(emplist.size() + "건");
		return emplist;
	}

}
