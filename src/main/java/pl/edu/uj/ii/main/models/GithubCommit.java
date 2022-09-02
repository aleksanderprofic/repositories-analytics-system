package pl.edu.uj.ii.main.models;

import com.spotify.github.v3.repos.Commit;
import lombok.Value;

@Value
public class GithubCommit {
    Commit githubCommit;
}
