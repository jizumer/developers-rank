package com.github.jizumer.developer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeveloperResponse {
    private String name;
    private String username;
    private String email;
    private int numberOfCommits;

}
