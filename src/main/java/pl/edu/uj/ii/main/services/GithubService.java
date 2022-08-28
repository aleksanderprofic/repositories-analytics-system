package pl.edu.uj.ii.main.services;

import org.apache.commons.lang3.time.DateUtils;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.ii.main.models.Branch;
import pl.edu.uj.ii.main.models.CommitChangesAmount;
import pl.edu.uj.ii.main.models.Commit;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class GithubService {

    private final GitHub github;

    @Autowired
    public GithubService(GitHub github) {
        this.github = github;
    }

    public List<Branch> getCommits(final String name, String inputDateSince, String inputDateUntil) throws IOException, ParseException {
        final GHRepository ghRepository = github.getRepository(name);
        final Map<String, GHBranch> namesToBranches = ghRepository.getBranches();
        final List<Branch> branches = new ArrayList<>();

        for (final Map.Entry<String, GHBranch> entry : namesToBranches.entrySet()) {
            final String branchName = entry.getKey();
            final List<Commit> branchCommits = new ArrayList<>();

            Long threeDayBeforeNow = DateUtils.addDays(new Date(),-3).getTime(); // since
            Long timestampNow = new Timestamp(System.currentTimeMillis()).getTime(); // until

            for (final GHCommit githubCommit : ghRepository.queryCommits().since(checkDate(inputDateSince, threeDayBeforeNow))
                    .until(checkDate(inputDateUntil, timestampNow)).from(branchName).list()) {
                final List<String> parentsSha = githubCommit.getParentSHA1s();
                final String message = githubCommit.getCommitShortInfo().getMessage();
                final long time = githubCommit.getCommitDate().getTime();
                final String sha = githubCommit.getSHA1();
                final Commit commit = new Commit(message, time, sha, parentsSha);

                branchCommits.add(commit);
            }
            if (!branchCommits.isEmpty()) {
                branches.add(new Branch(branchName, branchCommits));
            }
        }
        return branches;
    }

    private Long checkDate(String date, Long defaultDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        if (date.isEmpty()) {
            return defaultDate;
        } else {
            return formatter.parse(date).getTime();
        }
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
