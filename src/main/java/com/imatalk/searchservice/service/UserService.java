package com.imatalk.searchservice.service;

import com.imatalk.searchservice.dto.response.FriendRequestDTO;
import com.imatalk.searchservice.dto.response.UserProfile;
import com.imatalk.searchservice.entity.User;
import com.imatalk.searchservice.relationRepository.FriendRequestRepo;
import com.imatalk.searchservice.relationRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final FriendRequestRepo friendRequestRepo;
    public UserProfile getUserById(String currentUserId) {
        User user = userRepository.findById(currentUserId).orElse(null);

        if (user == null) {
            return null;
        }
        return new UserProfile(user);
    }

    public UserProfile getUserByEmail(String keyword) {
        User user = userRepository.findByEmail(keyword).orElse(null);

        if (user == null) {
            return null;
        }
        return new UserProfile(user);
    }

    public List<UserProfile> searchByUsernameStartsWith(String keyword) {
        List<User> users = userRepository.findAllByUsernameStartsWithIgnoreCase(keyword);
        return users.stream()
                .map(UserProfile::new)
                .toList();
    }

    public List<UserProfile> searchByDisplayNameStartsWith(String keyword) {
        List<User> users = userRepository.findAllByDisplayNameStartsWithIgnoreCase(keyword);
        log.info("Search by display name: {}", users.size());
        return users.stream()
                .map(UserProfile::new)
                .toList();
    }

    public List<FriendRequestDTO> findAllUnacceptedFriendRequestsByUserId(UserProfile currentUser) {
        User user  = userRepository.findById(currentUser.getId()).orElse(null);
        List<FriendRequestDTO> friendRequests = friendRequestRepo.findAllByReceiverAndIsAccepted(user, false)
                .stream()
                .map(FriendRequestDTO::new)
                .toList();
        return friendRequests;
    }
}
