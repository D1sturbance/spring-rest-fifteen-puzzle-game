package com.d1.repository;

import com.d1.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class UserRepository {

    private final List<User> userList = new ArrayList<>();

    public List<User> findAll() {
        return userList;
    }

    public User findById(String id) {
        return userList.stream().filter(user -> id.equals(user.getId())).findAny().orElse(null);
    }

    public void save(User user) {
        userList.add(user);
    }

    public void replace(User user) {
        userList
                .set(IntStream
                        .range(0, userList.size())
                        .filter(i -> user.getId().equals(userList.get(i).getId()))
                        .findFirst().orElse(-1), user);
    }

}
