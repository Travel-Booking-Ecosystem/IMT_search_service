package com.imatalk.searchservice.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PeopleDTO {
    // used in SearchController when user search for people

    private String id;
    private String username; // this is unique, like @john_due21
    private String displayName;
    private String email;
    private String avatar;
    private boolean isRequestSent;

    public PeopleDTO( UserProfile user) {
        // currentUser is the user who is searching for people
        // user is the user that is being searched
        this.id = user.getId();
        this.username = user.getUsername();
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
    }





}
