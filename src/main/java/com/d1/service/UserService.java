package com.d1.service;

import com.d1.factory.UserFactory;
import com.d1.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserService(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public void create(String userName, String gameName) {
        userRepository.save(userFactory.create(userName, gameName));
    }
}
