package com.ssk.springboot.service.posts;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssk.springboot.domain.posts.Posts;
import com.ssk.springboot.domain.posts.PostsRepository;
import com.ssk.springboot.web.dto.PostsResponseDto;
import com.ssk.springboot.web.dto.PostsSaveRequestDto;
import com.ssk.springboot.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {

	private final PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}
	
	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		
		//Jpa Entity에서의 업데이트는 해당 객체의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다 (더티체킹)
		posts.update(requestDto.getTitle(),requestDto.getContent());
		
		return id;
	}
	
	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		
		return new PostsResponseDto(entity);
	}
	
}
