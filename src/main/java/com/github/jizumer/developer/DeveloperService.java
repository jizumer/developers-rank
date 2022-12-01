package com.github.jizumer.developer;

import com.github.jizumer.commit.Commit;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DeveloperService {

    private final List<Developer> developersStore = new ArrayList<>() {{
        add(Developer.builder()
                .name("John Doe")
                .username("john.doe")
                .email("john.doe@test.com")
                .numberOfCommits(0)
                .build());
        add(Developer.builder()
                .name("Charles Barrow")
                .username("charles.barrow")
                .email("charles.barrow@test.com")
                .numberOfCommits(0)
                .build());
        add(Developer.builder()
                .name("John Summer")
                .username("john.summer")
                .email("john.summer@test.com")
                .numberOfCommits(0)
                .build());
    }};


    public DeveloperResponse getDeveloperByUsername(String username) {
        return findDeveloperByUsername(username).toResponse();
    }

    private Developer findDeveloperByUsername(String username) {
        return developersStore.stream().filter(developer -> developer.getUsername().equals(username))
                .findFirst()
                .orElseThrow();
    }

    @Incoming("commit-events")
    public void consumeCommitEvents(Commit commitEvent) {
        findDeveloperByUsername(commitEvent.getUsername())
                .addNumberOfCommits(1);
        System.out.println("Received commit event: " + commitEvent);
    }
}
