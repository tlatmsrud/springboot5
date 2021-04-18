package com.ssk.springboot.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest //별다른 설정없이 SpringBootTest를 사용할 경우 H2 데이터 베이스를 자동으로 실행
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	
	//After : 테스트 단위가 끝날때마다 수행되는 메서드를 지정하는 어노테이션
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void 게시글저장_불러오기() {
		
		String title = "테스트 게시글";
		String content = "테스트 본문";
		
		//builder 클래스를 통해 생성자 생성 후 save (insert/update)
		postsRepository.save(Posts.builder()
								.title(title)
								.content(content)
								.author("sksim@gmail.com")
								.build());
		
		List<Posts> postsList = postsRepository.findAll();
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle()).isEqualTo(title);
		assertThat(posts.getContent()).isEqualTo(content);
	}
	
	/*
	 * @Test public void BaseTimeEntity_등록() { //given LocalDateTime now =
	 * LocalDateTime.of(2019, 6,4,0,0,0); postsRepository.save(Posts.builder()
	 * .title("title") .content("content") .author("author") .build());
	 * 
	 * //when List<Posts> postsList = postsRepository.findAll();
	 * 
	 * //then Posts posts = postsList.get(0);
	 * 
	 * System.out.println(">>>>>>>> now = "+now+", create Date = "+posts.
	 * getCreatedDate()+", modefied Date = "+posts.getModifiedDate());
	 * 
	 * assertThat(posts.getCreatedDate()).isAfter(now);
	 * assertThat(posts.getModifiedDate()).isAfter(now);
	 * 
	 * }
	 */
}
