package com.github.jizumer;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeveloperService {

    public DeveloperResponse findDeveloperById(int id) {
        return DeveloperResponse.builder().id(id).name("John Doe").username("john.doe")
            .email("john.doe@test.com").build();
    }
}
