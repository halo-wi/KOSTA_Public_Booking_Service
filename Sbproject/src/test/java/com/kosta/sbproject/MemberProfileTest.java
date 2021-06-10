package com.kosta.sbproject;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.sbproject.model.MemberDTO;
import com.kosta.sbproject.model.MemberRoleEnumType;
import com.kosta.sbproject.model.ProfileDTO;
import com.kosta.sbproject.persistence.MemberRepository;
import com.kosta.sbproject.persistence.ProfileRepository;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class MemberProfileTest {
	@Autowired
	MemberRepository memberRepo;

	@Autowired
	ProfileRepository profileRepo;
	
	@Test
	public void sample6() {		
		memberRepo.getMemberWithProfileCount1().forEach(arr->{
			System.out.println(Arrays.toString(arr));
		});
		
	}

	//@Test
	public void sample5() {
		// member의 모든 profile, member정보를 알아내기
		MemberDTO mem = new MemberDTO().builder().mid("hoos1").build();
		profileRepo.findByMember(mem).forEach(prof->{
			System.out.println(prof);
			System.out.println(prof.getMember().getMname());
		});
		
	}
	
	//@Test
	public void Sample4() {
		profileRepo.findById(170L).ifPresent(prof->{
			System.out.println(prof);
			System.out.println(prof.getFname());
			System.out.println(prof.getMember().getMname());
		});
	}
	
	//@Test
	public void sample3() {
		MemberDTO member = new MemberDTO();
		member.setMid("hoos1");
		IntStream.range(1, 4).forEach(i->{
			ProfileDTO profile = new ProfileDTO();
			profile.setFname("face"+i+".jpg");
			if(i == 1) {
				profile.setCurrenYn(true);				
			} else {
				profile.setCurrenYn(false);
			}
			profile.setMember(member);
			profileRepo.save(profile);
		});
		MemberDTO member2 = new MemberDTO();
		member2.setMid("hoosBuilder4");
		IntStream.range(1, 5).forEach(i->{
			ProfileDTO profile = new ProfileDTO();
			profile.setFname("flower"+i+".jpg");
			if(i == 1) {
				profile.setCurrenYn(true);				
			} else {
				profile.setCurrenYn(false);
			}
			profile.setMember(member2);
			profileRepo.save(profile);
		});
	}
	
	//@Test
	public void sample2() {
		memberRepo.findAll().forEach(m->{
			log.info(m.toString());
		});
	}
	
	//@Test
	public void sample1() {
		IntStream.range(1, 4).forEach(i->{
			MemberDTO member = new MemberDTO();
			member.setMid("hoos"+i);
			member.setMname("남후승"+i);
			member.setMpassword("1234");
			member.setMrole(MemberRoleEnumType.ADMIN);
			memberRepo.save(member);
		});
		IntStream.range(4, 7).forEach(i-> {
			MemberDTO member = MemberDTO.builder()
					.mid("hoosBuilder"+i)
					.mname("코스타")
					.mpassword("1234")
					.mrole(MemberRoleEnumType.ADMIN)
					.build();
			memberRepo.save(member);
		});
		IntStream.range(7, 10).forEach(i-> {
			MemberDTO member = new MemberDTO("hoosConstructor"+i,"후승"+i, "1234", MemberRoleEnumType.ADMIN);
			memberRepo.save(member);
		});
	}
	
	
	
	
}
