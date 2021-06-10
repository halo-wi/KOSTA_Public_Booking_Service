package com.kosta.sbproject;

import java.sql.Date;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.kosta.sbproject.model.DeptVO;
import com.kosta.sbproject.model.EmpVO;
import com.kosta.sbproject.persistence.DeptVORepository;

import lombok.extern.java.Log;

@Log
@Commit
@SpringBootTest
public class DeptTestJPA {
   @Autowired
   DeptVORepository repo;
   
   @Transactional
   @Test
   public void deptEmpInsert() {
      //3번 부서에 직원을 10명 입력하기
      repo.findById(665).ifPresent(dept->{
         List<EmpVO> emplist = dept.getEmplist();
         IntStream.range(1, 11).forEach(idx->{
            EmpVO emp = EmpVO.builder()
                  .commissionPct(0.2)
                  .email("abc@naver.com")
                  .employeeId(idx+100)
                  .firstName("직원이름"+idx)
                  .hireDate(new Date(System.currentTimeMillis()))
                  .jobId("IT_PROG")
                  .lastName("가산")
                  .managerId(100)
                  .phoneNumber("010-1234-567"+idx)
                  .salary(2000)
                  .dept(dept) //부서코드주는것이 아니다. 부서객체를 주어야한다.
                  .build();
            emplist.add(emp);
         });
         repo.save(dept);
      });
      
   }
}