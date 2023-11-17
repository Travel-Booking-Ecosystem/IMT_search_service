package com.imatalk.searchservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString(exclude = {"friends"}) // to prevent infinite loop when toString() is called
public class User {

    @Id
    private String id;
    // TODO: remove firstName and lastName, use displayName instead
    private String displayName;
    private String username; // this is unique, like @john_due21
    private String email;
    private String password;
    private String avatar;
    private LocalDateTime joinAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> friends;




    public List<User> getFriends() {
        if (friends == null) {
            friends = new ArrayList<>();
        }
        return friends;
    }
}
