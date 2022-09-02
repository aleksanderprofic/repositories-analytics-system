package pl.edu.uj.ii.main.models;

import com.spotify.github.v3.repos.CommitItem;
import lombok.Value;

import java.util.List;

@Value
public class GithubBranch {
    String name;
    List<CommitItem> commits;
}
