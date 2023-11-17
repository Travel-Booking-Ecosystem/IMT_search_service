package com.imatalk.searchservice.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRegisteredUserEvent {
    private String userId;
    private String username;
    private String displayName;
    private String avatar;
}
