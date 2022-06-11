package pl.edu.uj.ii.main.models;

import com.spotify.github.v3.repos.Commit;
import lombok.Data;

@Data
public class GithubCommit {
    final Commit githubCommit;
}
