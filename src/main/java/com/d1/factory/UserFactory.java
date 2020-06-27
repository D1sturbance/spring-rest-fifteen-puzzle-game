package com.d1.factory;

import com.d1.domain.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserFactory {

    private final GameFactory gameFactory;

    public UserFactory(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    public User create(String userName, String gameName) {
        return new User(UUID.randomUUID().toString(), userName, gameFactory.create(gameName));
    }

}
