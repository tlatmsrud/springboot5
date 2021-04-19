package com.ssk.springboot.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssk.springboot.domain.posts.Posts;
import com.ssk.springboot.web.dto.PostsListResponseDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void 메인페이지_로딩() {
		String body=this.restTemplate.getForObject("/",String.class);
		
		assertThat(body).contains("스프링 부트로 시작하는 웹서비스");
	}
	
	@Test
	public void test() {
		List<Posts> list = new ArrayList<Posts>();
		list.add(Posts.builder()
					.title("title")
					.content("content")
					.author("author")
					.build()
		);
		list.add(Posts.builder()
				.title("title2")
				.content("content2")
				.author("author2")
				.build()
		);
		
		list.stream().
		map(PostsListResponseDto::new)
		.collect(Collectors.toList());
		
		//stream.map -> stream의 각 값을 PostsListResponseDto 객체로 변환 및 생성
		//stream.map.collection -> stream의 각 값을 PostsListResponseDto 객체로 변환 및 생성한 후 collect 메서드를 사용해 list 형식으로 변환한다.
		//결론 : List<Posts> 데이터를 List<PostsListResponseDto> 로 변환
		System.out.println(list.stream());
		System.out.println(list.stream().
				map(PostsListResponseDto::new));
	}

}
