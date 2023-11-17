package com.imatalk.searchservice.service;


import com.imatalk.searchservice.dto.response.CommonResponse;
import com.imatalk.searchservice.dto.response.FriendRequestDTO;
import com.imatalk.searchservice.dto.response.PeopleDTO;
import com.imatalk.searchservice.dto.response.UserProfile;
import com.imatalk.searchservice.events.NewMessageEvent;
import com.imatalk.searchservice.events.NewRegisteredUserEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SearchService {
    private final UserService userService;

    public ResponseEntity<?> searchPeople(String currentUserId, String keyword) {
        log.info("Search people with keyword: {}", keyword);

        UserProfile currentUser = userService.getUserById(currentUserId);
        // if keyword is empty, return nothing
        List<UserProfile> people = new ArrayList();
        if (keyword.isBlank()) {
            CommonResponse response = CommonResponse.success("Search user successfully",people);
            return ResponseEntity.ok(response);
        }

        people = search(keyword);
        // exclude the current user from the search result
        people = people.stream().filter(userProfile -> !userProfile.getId().equals(currentUser.getId())).toList();

        log.info("Convert to DTO");
        List<FriendRequestDTO> allSentFriendRequests = userService.findAllUnacceptedFriendRequestsByUserId(currentUser);

        List<PeopleDTO> result = new ArrayList<>();
        for (UserProfile userProfile : people) {
            // check if the current user has sent a friend request to the user in the search result
            boolean requestSent = allSentFriendRequests.stream()
                    .anyMatch(friendRequest -> friendRequest.getSender().getId().equals(userProfile.getId()));
            PeopleDTO peopleDTO = new PeopleDTO(userProfile);
            peopleDTO.setRequestSent(requestSent);
            result.add(peopleDTO);
        }




        log.info("Convert to DTO done");

        CommonResponse response = CommonResponse.success("Search user successfully", result);
        return ResponseEntity.ok(response);
    }

    private List<UserProfile> search(String keyword) {
        List<UserProfile> people = new ArrayList<>();
        // if keyword is not empty, return list of users that match the keyword
        if (keyword.startsWith("@")) {
            log.info("Search by username");
            // if keyword starts with @, return the user with that username
            people = userService.searchByUsernameStartsWith(keyword);
            log.info("Found {} users", people.size());
        } else if (isEmail(keyword)) {
            // if keyword is an email, return the user with that email
            log.info("Search by email");
            UserProfile user = userService.getUserByEmail(keyword);
            if (user != null) {
                people.add(user);
            }
            log.info("Found {} users", people.size());
        } else {
            log.info("Search by display name");
            // otherwise, return the list of users that match the display name
            people = userService.searchByDisplayNameStartsWith(keyword);
            log.info("Found {} users", people.size());
        }

        return people;

    }

    private boolean isEmail(String keyword) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "[a-zA-Z0-9-]+(?:\\."+
                "[a-zA-Z0-9-]+)*$";
        return keyword.matches(emailRegex);
    }

    public ResponseEntity<?> searchMessages(String currentUserId, String keyword ) {
        return null;
    }

    public void indexNewRegisteredUser(NewRegisteredUserEvent newRegisteredUserEvent) {

        // TODO: write the logic to index the new registered user

    }

    public void indexNewMessage(NewMessageEvent newMessageEvent) {
        NewMessageEvent.Message message = newMessageEvent.getMessage();

        // TODO: write the logic to index the new message
    }
}
