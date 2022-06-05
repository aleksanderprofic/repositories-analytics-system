package pl.edu.uj.ii.main.models;

import lombok.Data;
import org.kohsuke.github.GHCommit;

import java.util.List;

@Data
public class CommitResult {
    private final String message;
    private final long time;
    private final String sha;
//    private final List<GHCommit> parents;
}
