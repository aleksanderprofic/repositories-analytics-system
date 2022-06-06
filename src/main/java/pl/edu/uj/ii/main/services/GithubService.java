package pl.edu.uj.ii.main.services;

import com.spotify.github.v3.clients.GitHubClient;
import com.spotify.github.v3.clients.RepositoryClient;
import com.spotify.github.v3.repos.Branch;
import com.spotify.github.v3.repos.CommitItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.ii.main.models.GithubRepository;

import java.util.List;
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

        final CompletableFuture<List<Branch>> branches = repositoryClient.listBranches();
        final CompletableFuture<List<CommitItem>> commits = repositoryClient.listCommits();

        return new GithubRepository(branches.get(), commits.get());
    }
}
