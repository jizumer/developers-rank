package com.github.jizumer;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Commit {
    private LocalDateTime timestamp;
    private String hash;
    private String username;
    private String repository;
    private String branch;
    private String comment;
    private int lines;
}
