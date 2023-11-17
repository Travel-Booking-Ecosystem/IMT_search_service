package com.imatalk.searchservice.dto.request;

import lombok.Data;

@Data
public class SendMessageRequest {
    private String conversationId;
    private String content;
    private String repliedMessageId;
}
