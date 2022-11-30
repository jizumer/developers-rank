package com.github.jizumer;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeveloperService {

    private List<Developer> developersStore = List.of(
        Developer.builder().id(1).name("John Doe").username("john.doe")
            .email("john.doe@test.com").build()
    );

    public DeveloperResponse findDeveloperById(int id) {
        return developersStore.stream().filter(developer -> id == developer.getId()).findFirst()
            .orElseThrow()
            .toResponse();
    }
}
