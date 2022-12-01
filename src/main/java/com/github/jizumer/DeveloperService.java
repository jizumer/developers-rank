package com.github.jizumer;

import org.eclipse.microprofile.rest.client.inject.RestClient;

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

    @RestClient
    private CommitGateway commitGateway;

    public DeveloperResponse getDeveloperByUsername(String username) {
        final var developerFound = findDeveloperByUsername(username);
        final var numberOfCommits = getNumberOfCommitsMadeByDeveloper(developerFound.getUsername());

        return developerFound.toResponse(numberOfCommits);
    }

    public Developer findDeveloperByUsername(String username) {
        return developersStore.stream().filter(developer -> developer.getUsername().equals(username))
                .findFirst()
                .orElseThrow();
    }

    public int getNumberOfCommitsMadeByDeveloper(String username) {
        return commitGateway.getAllCommitsMadeByDeveloper(username).size();
    }
}
