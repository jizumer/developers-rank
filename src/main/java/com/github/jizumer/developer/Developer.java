package com.github.jizumer.developer;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Developer {
    private String name;
    private String username;
    private String email;

    private int numberOfCommits;

    public DeveloperResponse toResponse() {
        return DeveloperResponse.builder()
                .name(name)
                .username(username)
                .email(email)
                .numberOfCommits(numberOfCommits)
                .build();
    }

    public void addNumberOfCommits(int numberOfCommits) {
        this.numberOfCommits += numberOfCommits;
    }
}
