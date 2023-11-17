package com.imatalk.searchservice.controller;

import com.imatalk.searchservice.dto.response.CommonResponse;
import com.imatalk.searchservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    // TODO: you will need to use ElasticSearch for this feature
    private final SearchService searchService;
    @GetMapping("/health")
    public ResponseEntity<CommonResponse> health() {
        Map<String, String> map = Map.of(
                "service", "search-service",
                "status", "OK",
                "time", LocalDateTime.now().toString());
        return ResponseEntity.ok(CommonResponse.success("Health check",map));
    }



    //TODO: don't use @RequestParam, use @RequestBody instead (for security reason)
    @GetMapping("/people")
    public ResponseEntity<?> searchPeople(@RequestHeader String currentUserId, @RequestParam String keyword) {
        log.info("CONTROLLER search people with keyword: {}", keyword);
        return searchService.searchPeople(currentUserId,keyword);
    }

    //TODO: don't use @RequestParam, use @RequestBody instead (for security reason)

    @GetMapping("/messages")
    public ResponseEntity<?> searchMessages(@RequestHeader String currentUserId, @RequestParam String keyword) {
        return searchService.searchMessages(currentUserId ,keyword);
    }


}
