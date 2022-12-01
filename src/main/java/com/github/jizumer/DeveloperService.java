package com.github.jizumer;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeveloperService {

    private final List<Developer> developersStore = List.of(
        Developer.builder().id(1).name("John Doe").username("john.doe")
            .email("john.doe@test.com").build(),
        Developer.builder().id(2).name("Charles Barrow").username("charles.barrow")
            .email("charles.barrow@test.com").build(),
        Developer.builder().id(3).name("John Summer").username("john.summer")
            .email("john.summer@test.com").build()
    );

    public DeveloperResponse findDeveloperById(int id) {
        return developersStore.stream().filter(developer -> id == developer.getId()).findFirst()
            .orElseThrow()
            .toResponse();
    }
}
