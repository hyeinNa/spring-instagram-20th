package com.ceos20.spring_instagram.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Getter
@Setter
@Builder // 빌더 패턴 적용
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드 포함 생성자
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 기능 활성화
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private String text;

    @CreatedDate
    private LocalDateTime timestamp;
}