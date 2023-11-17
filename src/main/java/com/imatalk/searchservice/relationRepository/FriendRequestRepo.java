package com.imatalk.searchservice.relationRepository;

import com.imatalk.searchservice.entity.FriendRequest;
import com.imatalk.searchservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepo extends JpaRepository<FriendRequest, String> {

    Optional<FriendRequest> findBySenderAndReceiver(User sender, User receiver);
    List<FriendRequest> findAllByReceiver(User receiver);

    List<FriendRequest> findAllByReceiverAndIsAccepted(User currentUser, boolean isAccepted);

    List<FriendRequest> findAllBySenderAndIsAccepted(User currentUser, boolean b);
}
