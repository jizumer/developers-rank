package com.github.jizumer.developer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Developer {
    private String name;
    private String username;
    private String email;

    public DeveloperResponse toResponse(int numberOfCommits) {
        return DeveloperResponse.builder()
                .name(name)
                .username(username)
                .email(email)
                .numberOfCommits(numberOfCommits)
                .build();
    }
}
