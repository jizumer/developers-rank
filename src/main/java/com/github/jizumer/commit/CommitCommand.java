package com.github.jizumer.commit;

import com.github.jizumer.shared.Command;
import lombok.Getter;

@Getter
public class CommitCommand implements Command {

    private Commit commit;
}
