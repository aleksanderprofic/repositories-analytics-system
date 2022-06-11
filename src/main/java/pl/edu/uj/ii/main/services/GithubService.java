package pl.edu.uj.ii.main.services;

import com.spotify.github.v3.clients.GitHubClient;
import com.spotify.github.v3.clients.RepositoryClient;
import com.spotify.github.v3.repos.Branch;
import com.spotify.github.v3.repos.Commit;
import com.spotify.github.v3.repos.CommitItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.ii.main.models.GithubCommit;
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

    private RepositoryClient getRepositoryClient(final String name) {
        final String[] ownerAndNameSplit = name.split("/");
        return gitHubClient.createRepositoryClient(ownerAndNameSplit[0], ownerAndNameSplit[1]);
    }

    public GithubRepository getRepositoryInfo(final String name) throws ExecutionException, InterruptedException {
        final RepositoryClient repositoryClient = getRepositoryClient(name);

        final CompletableFuture<List<Branch>> branches = repositoryClient.listBranches();
        final CompletableFuture<List<CommitItem>> commits = repositoryClient.listCommits();

        return new GithubRepository(branches.get(), commits.get());
    }

    public GithubCommit getCommitInfo(final String name, final String commitSha) throws ExecutionException, InterruptedException {
        final RepositoryClient repositoryClient = getRepositoryClient(name);
        
        final CompletableFuture<Commit> githubCommit= repositoryClient.getCommit(commitSha);

        return new GithubCommit(githubCommit.get());
    }
}
