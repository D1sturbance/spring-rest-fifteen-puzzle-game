package com.d1.domain;

import lombok.Value;

@Value
public class User {
    String id;
    String name;
    Game game;

}
