package com.ceos20.spring_instagram.repository;
import com.ceos20.spring_instagram.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ceos20.spring_instagram.domain.Comment;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}