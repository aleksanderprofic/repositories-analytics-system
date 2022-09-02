package pl.edu.uj.ii.main.models;

import com.spotify.github.v3.repos.CommitComparison;
import lombok.Value;

@Value
public class GithubCommitComparison {
    CommitComparison githubCommitComparison;
}
