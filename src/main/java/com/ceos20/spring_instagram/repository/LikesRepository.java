package com.ceos20.spring_instagram.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ceos20.spring_instagram.domain.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
}
