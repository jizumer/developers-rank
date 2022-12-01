package com.github.jizumer;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DeveloperService {

    private final List<Developer> developersStore = List.of(
            Developer.builder().name("John Doe").username("john.doe")
                    .email("john.doe@test.com").build(),
            Developer.builder().name("Charles Barrow").username("charles.barrow")
                    .email("charles.barrow@test.com").build(),
            Developer.builder().name("John Summer").username("john.summer")
                    .email("john.summer@test.com").build()
    );

    public DeveloperResponse findDeveloperByUsername(String username) {
        return developersStore.stream().filter(developer -> developer.getUsername().equals(username)).findFirst()
                .orElseThrow()
                .toResponse();
    }
}
