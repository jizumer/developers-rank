package com.github.jizumer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Developer {
    private String name;
    private String username;
    private String email;

    public DeveloperResponse toResponse() {
        return DeveloperResponse.builder()
            .name(name)
            .username(username)
            .email(email)
            .build();
    }
}
