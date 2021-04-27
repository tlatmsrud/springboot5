package com.ssk.springboot;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ssk.springboot.config.auth.SecurityConfig;
import com.ssk.springboot.web.HelloController;

@RunWith(SpringRunner.class) //스프링 실행자를 JUnit 내에 저장된 실행자와 함께 실행
@WebMvcTest(controllers = HelloController.class, // Mvc Test에 사용하는 어노테이션, 테스트 컨트롤러 리스트에 HelloController를 추가
		excludeFilters = {
				@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
		}
) 
public class HelloControllerTest {
	
	@Autowired
	private MockMvc mvc; //mvc 테스트용 객체
	
	@WithMockUser(roles="USER")
	@Test
	public void hello가_리턴된다() throws Exception{
		String hello = "hello";
		mvc.perform(get("/hello")) //hello로 get 통신
			.andExpect(status().isOk()) //200인지 검사
			.andExpect(content().string(hello)); //response 값이 hello 인지 검사
		
	}
	
	@WithMockUser(roles="USER")
	@Test
	public void helloDto가_리턴된다() throws Exception{
		String name = "hello";
		int amount = 1000;
		
		mvc.perform(get("/hello/dto")
				.param("name", name)
				.param("amount", String.valueOf(amount)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(name)))
				.andExpect(jsonPath("$.amount", is(amount)));
	}
}
