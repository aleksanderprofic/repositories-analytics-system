package pl.edu.uj.ii.main.services;

import com.spotify.github.v3.clients.GitHubClient;
import com.spotify.github.v3.clients.RepositoryClient;
import com.spotify.github.v3.repos.Branch;
import com.spotify.github.v3.repos.CommitItem;
import com.spotify.github.v3.repos.Languages;
import com.spotify.github.v3.repos.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.ii.main.models.GithubRepository;
import pl.edu.uj.ii.main.models.PercentageLanguages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class GithubService {

    private final GitHubClient gitHubClient;

    @Autowired
    public GithubService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public GithubRepository getRepositoryInfo(final String name) throws ExecutionException, InterruptedException {
        final String[] ownerAndNameSplit = name.split("/");
        final RepositoryClient repositoryClient = gitHubClient.createRepositoryClient(ownerAndNameSplit[0], ownerAndNameSplit[1]);

        final CompletableFuture<Languages> languages = repositoryClient.getLanguages();
        final CompletableFuture<Repository> repository = repositoryClient.getRepository();
        final CompletableFuture<List<Branch>> branches = repositoryClient.listBranches();
        final CompletableFuture<List<CommitItem>> commits = repositoryClient.listCommits();

        final PercentageLanguages percentageLanguages = computeLanguagePercentages(languages.get());
        return new GithubRepository(repository.get(), branches.get(), commits.get(), percentageLanguages);
    }

    private PercentageLanguages computeLanguagePercentages(final Languages languageStatistics) {
        final Collection<Integer> values = languageStatistics.values();
        final int valuesSum = values.stream().reduce(0, Integer::sum);
        final PercentageLanguages percentageLanguages = new PercentageLanguages();

        if (valuesSum > 0) {
            for (final Map.Entry<String, Integer> entry : languageStatistics.entrySet()) {
                final String languageName = entry.getKey();
                final BigDecimal percentageValue = BigDecimal.valueOf(((double)entry.getValue()) / valuesSum * 100D);

                percentageLanguages.put(languageName, percentageValue.setScale(2, RoundingMode.HALF_UP));
            }
        }
        return percentageLanguages;
    }
}
