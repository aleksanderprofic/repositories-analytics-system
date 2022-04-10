package pl.edu.uj.ii.main.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class GitService {

    public String getRepository(final String url) {
        try (Repository repository = Git.cloneRepository().setCloneAllBranches(true).setURI(url).call().getRepository()){
            System.out.println(repository);
            System.out.println(repository.getAdditionalHaves());
            System.out.println(repository.getAllRefsByPeeledObjectId());

            Git git = new Git(repository);

            for (Ref branch : git.branchList().call()){
                git.checkout().setName(branch.getName()).call();
                // Then just revwalk as normal.
                Iterable<RevCommit> log = git.log().call();
                for (RevCommit rev : log) {
                    System.out.println("Branch: " + branch);
                    System.out.println("Author: " + rev.getAuthorIdent().getName());
                    System.out.println("Parents: " + Arrays.toString(new Stream[]{Arrays.stream(rev.getParents()).map(AnyObjectId::name)}));
                    System.out.println("Message: " + rev.getFullMessage());
                    System.out.println("Commit time: " + rev.getCommitTime());
                    System.out.println("SHA1: " + rev.name());
                }
            }
// jaki branch, referencje do rodziców: [sha tylko], sha, data
//            RevWalk walk = new RevWalk(repository);
//            walk.markStart(walk.parseCommit(repository.resolve("HEAD")));
//            for (RevCommit rev : walk) {
////                It never cames in this block
//
//                System.out.println(rev.getAuthorIdent().getName());
//                System.out.println(rev.getFullMessage());
//            }
        } catch (GitAPIException | IOException e) {
            e.printStackTrace();
        }



        return url;
    }

//    public String getRepository(final String url) {
//        try {
//            System.out.println("Listing remote repository " + url);
//            Collection<Ref> refs = Git.lsRemoteRepository()
//                    .setHeads(true)
//                    .setTags(true)
//                    .setRemote(url)
//                    .call();
//
//            for (Ref ref : refs) {
//                System.out.println("Ref: " + ref);
//                Ref ref2;
//                while((ref2 = ref.getLeaf()) != ref) {
//                    System.out.println("Ref: " + ref2);
//                }
//            }
//
//            final Map<String, Ref> map = Git.lsRemoteRepository()
//                    .setHeads(true)
//                    .setTags(true)
//                    .setRemote(url)
//                    .callAsMap();
//
//            System.out.println("As map");
//            for (Map.Entry<String, Ref> entry : map.entrySet()) {
//                System.out.println("Key: " + entry.getKey() + ", Ref: " + entry.getValue());
//            }
//
//            refs = Git.lsRemoteRepository()
//                    .setRemote(url)
//                    .call();
//
//            System.out.println("All refs");
//            for (Ref ref : refs) {
//                System.out.println("Ref: " + ref);
//            }
//        } catch (GitAPIException e) {
//            e.printStackTrace();
//        }
//        return url;
//    }
}
