package pl.edu.uj.ii.main.models;

import com.spotify.github.v3.repos.Repository;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Value
public class GithubRepository {
    Repository repositoryStatistics;
    List<GithubBranch> branches;
    Map<String, BigDecimal> languages;
}
