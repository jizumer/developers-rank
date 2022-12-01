package com.github.jizumer;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CommitService {

    @ConfigProperty(name = "lag")
    Integer lag;

    private final List<Commit> commitStore = new ArrayList<>();

    public List<Commit> findAllCommits() {
        return commitStore.stream().toList();
    }

    public void save(Commit commit) {
        commitStore.add(commit);
    }

    public List<Commit> findAllCommitsMadeByDeveloper(String username) {

        return commitStore.stream().filter(commit -> commit.getUsername().equals(username)).toList();
    }

    public void clearCommits() {
        commitStore.clear();
    }
}
