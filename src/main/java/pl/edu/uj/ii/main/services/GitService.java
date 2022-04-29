package pl.edu.uj.ii.main.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.stereotype.Service;
import pl.edu.uj.ii.main.models.CommitResult;
import pl.edu.uj.ii.main.models.RepositoryResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GitService {

    public List<RepositoryResult> getRepository(final String url) {
        final List<RepositoryResult> results = new ArrayList<>();
        try (Git git = Git.cloneRepository().setURI(url).call()) {
            final Set<Ref> branches = git
                    .branchList()
                    .setListMode(ListBranchCommand.ListMode.ALL)
                    .call()
                    .stream()
                    .filter(el -> el.getName().contains("refs/remotes"))
                    .collect(Collectors.toSet());

            for (final Ref ref : branches) {
                System.out.println("Branch name: " + ref.getName());
                System.out.println("Branch: " + ref.getObjectId().name());
                git.checkout().setName(ref.getObjectId().name()).call();

                final List<CommitResult> commitResults = new ArrayList<>();
                for (final RevCommit rev : git.log().call()) {
                    commitResults.add(new CommitResult(rev.getShortMessage(), rev.getCommitTime(), rev.name(), Arrays.stream(rev.getParents()).map(AnyObjectId::name).collect(Collectors.toList())));
                    System.out.println("=====================");
                    System.out.println("Author: " + rev.getAuthorIdent().getName());
                    System.out.println("Parents: " + Arrays.stream(rev.getParents()).map(AnyObjectId::name).collect(Collectors.toList()));
                    System.out.println("Message: " + rev.getShortMessage());
                    System.out.println("Commit time: " + rev.getCommitTime());
                    System.out.println("SHA1: " + rev.name());
                }
                final RepositoryResult result = new RepositoryResult(ref.getName(), commitResults);
                results.add(result);
            }
// jaki branch, referencje do rodziców: [sha tylko], sha, data
//            RevWalk walk = new RevWalk(repository);
//            walk.markStart(walk.parseCommit(repository.resolve("HEAD")));
//            for (RevCommit rev : walk) {
//
//                System.out.println(rev.getAuthorIdent().getName());
//                System.out.println(rev.getFullMessage());
//            }
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return results;
    }
}
