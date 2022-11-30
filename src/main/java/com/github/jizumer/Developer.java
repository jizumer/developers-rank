package com.github.jizumer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Developer {
    private int id;
    private String name;
    private String username;
    private String email;
}
