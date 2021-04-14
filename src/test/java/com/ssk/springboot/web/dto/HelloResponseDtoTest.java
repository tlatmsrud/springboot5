package com.ssk.springboot.web.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class HelloResponseDtoTest {

	@Test
	public void 롬복_기능_테스트() {
		
		String name = "test";
		int amount = 1000;
		
		HelloResponseDto dto = new HelloResponseDto(name,amount);
		
		assertThat(dto.getName()).isEqualTo(name); //assertj라는 테스트 라이브러리의 테스트 메소드, 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용
		assertThat(dto.getAmount()).isEqualTo(amount);
	}
}
