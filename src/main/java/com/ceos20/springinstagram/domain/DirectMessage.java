package com.ceos20.springinstagram.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class DirectMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiver;

    private String text;
    private LocalDateTime timestamp;

    /*@PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
    */
}
