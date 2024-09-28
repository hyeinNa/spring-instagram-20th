package com.ceos20.springinstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ceos20.springinstagram.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}