package com.ssk.springboot.domain.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<Posts, Long>{ //Entity 클래스, PK 타입

	@Query("SELECT p FROM Posts p ORDER BY p.id DESC")
	List<Posts> findAllDesc();
}
