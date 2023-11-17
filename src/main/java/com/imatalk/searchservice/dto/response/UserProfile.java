package com.imatalk.searchservice.dto.response;

import com.imatalk.searchservice.entity.User;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserProfile {
    private String id;
    private String username; // this is unique, like @john_due21
    private String displayName;
    private String email;
    private String avatar;
    private LocalDateTime joinAt;


    public UserProfile(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.joinAt = user.getJoinAt();
    }
}



