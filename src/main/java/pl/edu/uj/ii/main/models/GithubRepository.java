package pl.edu.uj.ii.main.models;

import com.spotify.github.v3.repos.Branch;
import com.spotify.github.v3.repos.CommitItem;
import com.spotify.github.v3.repos.Repository;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class GithubRepository {
    private final Repository repositoryStatistics;
    private final List<Branch> branches;
    private final List<CommitItem> commits;
    private final Map<String, BigDecimal> languages;
}
