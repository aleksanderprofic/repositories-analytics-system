package pl.edu.uj.ii.main.models;

import com.spotify.github.v3.repos.CommitComparison;
import lombok.Data;

@Data
public class GithubCommitComparison {
    final CommitComparison githubCommitComparison;
}
