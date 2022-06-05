package pl.edu.uj.ii.main.models;

import lombok.Data;

import java.util.List;

@Data
public class GithubRepositoryResult {
    private final String branchName;
    private final List<CommitResult> commits;
}
