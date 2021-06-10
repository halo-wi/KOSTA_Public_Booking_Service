package com.kosta.sbproject;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kosta.sbproject.model.DeptVO;
import com.kosta.sbproject.persistence.DeptVORepository;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptVORepositoryTests {
	@Autowired
	DeptVORepository repo;
	
	//@Test
	public void test9() {		
		repo.findByDepartmentName("인사부2").forEach(d->{
			log.info(d.toString());
		});
	}

	//@Test
	public void test10() {		
		repo.selectAll().forEach(d->{
			log.info(d.toString());
		});
	}
	//@Test
	public void test11() {		
		repo.selectById(159).forEach(d->{
			log.info(d.toString());
		});
		repo.selectById2(159).forEach(d->{
			log.info(d.toString());
		});
		repo.selectById3(159).forEach(d->{
			log.info(d.toString());
		});
		repo.selectById4(159).forEach(d->{
			log.info(d.toString());
		});
	}
	
	
	//@Test
	public void test4() {
		Optional<DeptVO> dept = repo.findById(40); // null을 방지하는 Optional
		dept.ifPresent(d -> {
			log.info(d.toString());
			d.setDepartmentName("부서이름남후승");
			repo.save(d);
		});
		
	}
	
	//@Test
	public void test5() {
		Optional<DeptVO> dept = repo.findById(40); // null을 방지하는 Optional
		dept.ifPresent(d -> {
			log.info(d.toString());
			repo.delete(d);
		});
		
	}
	
	//@Test
	public void test6() {
		boolean result = repo.existsById(40);
		System.out.println(result?"존재함":"존재하지않음");
		
	}

	//@Test
	public void test7() {
		long rowCount = repo.count();
		
		System.out.println(rowCount+"건");
	}
	
	//@Test
	public void test3() {
		IntStream.range(21, 31).forEach(i -> {
			DeptVO d = DeptVO.builder()
					.departmentName("영업부"+i)
					.managerId(i)
					.locationId(i)
					.build();
			repo.save(d);
		});
	}
	
	@Test
	public void insertDept2() {
		IntStream.range(1, 11).forEach(i -> {
			DeptVO d1 = new DeptVO();
			d1.setDepartmentName("인사부"+i);
			d1.setLocationId(i);
			d1.setManagerId(i+10);
			repo.save(d1);			
		});
		for(int i=12; i<21; i++) {
			DeptVO d2 = new DeptVO();
			d2.setDepartmentName("개발부"+i);
			d2.setLocationId(i);
			d2.setManagerId(i+10);
			repo.save(d2);		
		}
	}

	//@Test
	public void test2() {
		repo.findAll().forEach(deptvo -> {
			log.info(deptvo.toString());
		});
	}
	
	//@Test
//	public void updateDept() {
//		dept.findById(4).ifPresent(deptvo-> {
//			deptvo.setDepartmentName("인사안함부1");
//			deptvo.setLocationId(203);
//			deptvo.setManagerId(103);
//			dept.save(deptvo);
//		});
//	}
	
	//	@Test
	public void deleteDept() {
		repo.deleteById(40);
	}
	
}
