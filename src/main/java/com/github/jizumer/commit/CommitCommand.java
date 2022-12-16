package com.github.jizumer.commit;

import com.github.jizumer.shared.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommitCommand implements Command {

    private Commit commit;
}
