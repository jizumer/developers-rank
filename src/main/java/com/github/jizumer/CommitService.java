package com.github.jizumer;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CommitService {

    private List<Commit> commitStore = new ArrayList<>();

    public List<Commit> findAllCommits() {
        return commitStore.stream().toList();
    }

    public void save(Commit commit) {
        commitStore.add(commit);
    }
}
