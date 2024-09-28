package com.ceos20.spring_instagram.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ceos20.spring_instagram.domain.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}