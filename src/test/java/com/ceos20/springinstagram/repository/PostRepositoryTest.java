package com.ceos20.springinstagram.repository;

import com.ceos20.springinstagram.domain.Post;
import com.ceos20.springinstagram.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager; // JPA 쿼리 확인을 위한 EntityManager

    @BeforeEach
    public void setup() {
        // Given - 3명의 유저를 저장
        User user1 = new User();
        user1.setUsername("user1");
        user1.setEmail("user1@test.com");
        user1.setPassword("password1");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setEmail("user2@test.com");
        user2.setPassword("password2");
        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("user3");
        user3.setEmail("user3@test.com");
        user3.setPassword("password3");
        userRepository.save(user3);

        // Given - 3개의 포스트를 저장
        Post post1 = new Post();
        post1.setUser(user1);
        post1.setImage("image1.jpg");
        post1.setCaption("caption1");
        post1.setTimestamp(LocalDateTime.now());
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setUser(user2);
        post2.setImage("image2.jpg");
        post2.setCaption("caption2");
        post2.setTimestamp(LocalDateTime.now());
        postRepository.save(post2);

        Post post3 = new Post();
        post3.setUser(user3);
        post3.setImage("image3.jpg");
        post3.setCaption("caption3");
        post3.setTimestamp(LocalDateTime.now());
        postRepository.save(post3);

        // JPA 쿼리를 확인하기 위해 flush
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void givenPosts_whenFindAll_thenReturnPosts() {
        // When - 모든 포스트를 조회
        List<Post> posts = postRepository.findAll();

        // Then - 조회된 포스트가 3개인지 확인
        assertThat(posts).hasSize(3);

        // Then - 각각의 포스트가 올바른지 확인
        assertThat(posts.get(0).getCaption()).isEqualTo("caption1");
        assertThat(posts.get(1).getCaption()).isEqualTo("caption2");
        assertThat(posts.get(2).getCaption()).isEqualTo("caption3");
    }
}
