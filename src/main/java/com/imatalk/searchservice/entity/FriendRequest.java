package com.imatalk.searchservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString(exclude = {"sender", "receiver"})
@Entity
public class FriendRequest {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER)
    private User receiver;
    private LocalDateTime createdAt;
    private boolean isAccepted;
}
