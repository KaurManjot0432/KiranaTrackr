package com.example.KiranaTrackr.services.user;

import com.example.KiranaTrackr.models.User;

public interface UserService {
    public User createUser(User user);
    public User getUserById(String userId);
}