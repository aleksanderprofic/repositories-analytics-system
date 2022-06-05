package pl.edu.uj.ii.main.services;

import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.ii.main.models.CommitChangesAmount;
import pl.edu.uj.ii.main.models.CommitParent;
import pl.edu.uj.ii.main.models.CommitResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GithubService {

    private final GitHub github;

    @Autowired
    public GithubService(GitHub github) {
        this.github = github;
    }

    public List<CommitResult> getCommits(final String name) throws IOException {
        final GHRepository ghRepository = github.getRepository(name);

        final Map<String, GHBranch> namesToBranches = ghRepository.getBranches();


        final List<CommitResult> results = new ArrayList<>();
        ghRepository.queryCommits().from("").list();

        for (GHCommit commit : ghRepository.listCommits()) {
            final List<String> parentsSha = commit.getParentSHA1s();
            final String message = commit.getCommitShortInfo().getMessage();
            final long time = commit.getCommitDate().getTime();
            final String sha = commit.getSHA1();
            final CommitResult commitResult = new CommitResult(message, time, sha, parentsSha);
            results.add(commitResult);
        }
        return results;
    }

    public List<CommitChangesAmount> getCommitChangesAmount(final String name) throws IOException {
        final GHRepository ghRepository = github.getRepository(name);

        final List<CommitChangesAmount> results = new ArrayList<>();
        for (GHCommit commit : ghRepository.listCommits()) {
            final CommitChangesAmount commitChangesAmount = new CommitChangesAmount(commit.getLinesAdded(),
                    commit.getLinesChanged(),
                    commit.getLinesDeleted());
            results.add(commitChangesAmount);
        }
        return results;
    }
}
