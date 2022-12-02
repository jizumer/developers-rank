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

    public Developer addNumberOfCommits(int numberOfCommits) {
        this.numberOfCommits += numberOfCommits;
        return this;
    }
}
