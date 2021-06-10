package com.kosta.sbproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.sbproject.model.CarVO;

import lombok.extern.java.Log;

//JUNIT
@Log
@SpringBootTest
class SbprojectApplicationTests {

	@Test
	void lombokTest2() {
		CarVO car1 = CarVO.builder()
				.model("BMW520")
				.price(5000)
				.company("BMW").build();
		System.out.println(car1);
		
		log.info(car1.toString());
	}
	
	//@Test
	void lombokTest1() {
		CarVO car1 = new CarVO();
		CarVO car2 = new CarVO("sm7",5000,"르노삼성");
		CarVO car3 = new CarVO("소나타",5000,"현대");
		CarVO car4 = new CarVO("소나타",5000,"현대");
		
		System.out.println(car1);
		System.out.println(car2);
		System.out.println(car3);
		System.out.println(car2.getModel());
		System.out.println(car1.equals(car2));
		System.out.println(car3.equals(car4));
	}
	
	@Test
	void contextLoads() {
	}
	

}
