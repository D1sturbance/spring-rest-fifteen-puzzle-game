package com.d1.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private long id;
    private String name;
    private Game game;

}
