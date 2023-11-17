package com.imatalk.searchservice.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateGroupConversationRequest {
    private String groupName;
    private List<String> memberIds;
}
