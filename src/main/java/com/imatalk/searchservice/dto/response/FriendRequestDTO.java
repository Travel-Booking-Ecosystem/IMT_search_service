package com.imatalk.searchservice.dto.response;


import com.imatalk.searchservice.entity.FriendRequest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendRequestDTO {
    private String id;
    private UserProfile sender;
    private LocalDateTime createdAt;
    private boolean isAccepted;

    public FriendRequestDTO(FriendRequest friendRequest) {
        this.id = friendRequest.getId();
        this.sender = new UserProfile(friendRequest.getSender());
        this.createdAt = friendRequest.getCreatedAt();
        this.isAccepted = friendRequest.isAccepted();
    }
}