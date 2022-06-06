package pl.edu.uj.ii.main.models;

import com.spotify.github.v3.repos.Branch;
import com.spotify.github.v3.repos.CommitItem;
import lombok.Data;

import java.util.List;

@Data
public class GithubRepository {
    private final List<Branch> branches;
    private final List<CommitItem> commits;
}