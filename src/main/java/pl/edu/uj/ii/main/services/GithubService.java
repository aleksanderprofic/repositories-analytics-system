package pl.edu.uj.ii.main.services;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.ii.main.models.CommitResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GithubService {

    private final GitHub github;
//    private final GithubClient githubClient;

    @Autowired
//    public GithubService(GithubClient githubClient) {
//        this.githubClient = githubClient;
//    }
    public GithubService(GitHub github) {
        this.github = github;
    }

    public List<CommitResult> getRepository(final String name) throws IOException {
        final GHRepository ghRepository = github.getRepository(name);

        final List<CommitResult> results = new ArrayList<>();
        for (GHCommit commit : ghRepository.listCommits()) {
            try {
                final CommitResult commitResult = new CommitResult(commit.getCommitShortInfo().getMessage(), commit.getCommitDate().getTime(), commit.getSHA1());
                results.add(commitResult);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return results;
    }
//    public List<GithubRepositoryResult> getRepository(final String url) {
//        final List<GithubRepositoryResult> results = new ArrayList<>();
//        try (Git git = Git.cloneRepository().setURI(url).call()) {
//            final Set<Ref> branches = git
//                    .branchList()
//                    .setListMode(ListBranchCommand.ListMode.ALL)
//                    .call()
//                    .stream()
//                    .filter(el -> el.getName().contains("refs/remotes"))
//                    .collect(Collectors.toSet());
//
//            for (final Ref ref : branches) {
//                System.out.println("Branch name: " + ref.getName());
//                System.out.println("Branch: " + ref.getObjectId().name());
//                git.checkout().setName(ref.getObjectId().name()).call();
//
//                final List<CommitResult> commitResults = new ArrayList<>();
//                for (final RevCommit rev : git.log().call()) {
//                    commitResults.add(new CommitResult(rev.getShortMessage(), rev.getCommitTime(), rev.name(), Arrays.stream(rev.getParents()).map(AnyObjectId::name).collect(Collectors.toList())));
//                    System.out.println("=====================");
//                    System.out.println("Author: " + rev.getAuthorIdent().getName());
//                    System.out.println("Parents: " + Arrays.stream(rev.getParents()).map(AnyObjectId::name).collect(Collectors.toList()));
//                    System.out.println("Message: " + rev.getShortMessage());
//                    System.out.println("Commit time: " + rev.getCommitTime());
//                    System.out.println("SHA1: " + rev.name());
//                }
//                final GithubRepositoryResult result = new GithubRepositoryResult(ref.getName(), commitResults);
//                results.add(result);
//            }
//// jaki branch, referencje do rodziców: [sha tylko], sha, data
////            RevWalk walk = new RevWalk(repository);
////            walk.markStart(walk.parseCommit(repository.resolve("HEAD")));
////            for (RevCommit rev : walk) {
////
////                System.out.println(rev.getAuthorIdent().getName());
////                System.out.println(rev.getFullMessage());
////            }
//        } catch (GitAPIException e) {
//            e.printStackTrace();
//        }
//        return results;
//    }
}
