package com.github.jizumer;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Builder
@ToString
public class Commit {
    private LocalDateTime timestamp;
    private String hash;
    private String username;
    private String repository;
    private String branch;
    private String comment;
    private int lines;
}
