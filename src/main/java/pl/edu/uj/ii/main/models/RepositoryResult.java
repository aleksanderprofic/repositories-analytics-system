package pl.edu.uj.ii.main.models;

import lombok.Data;

import java.util.List;

@Data
public class RepositoryResult {
    private final String branchName;
    private final List<CommitResult> commits;
}
