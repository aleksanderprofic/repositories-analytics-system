package pl.edu.uj.ii.main.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uj.ii.main.models.GithubCommit;
import pl.edu.uj.ii.main.models.GithubCommitComparison;
import pl.edu.uj.ii.main.models.GithubRepository;
import pl.edu.uj.ii.main.services.GithubService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/github")
@AllArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/repository")
    @CrossOrigin
    public GithubRepository getRepositoryInfo(@RequestParam("repositoryName") final String repositoryName) throws ExecutionException, InterruptedException {
        return githubService.getRepositoryInfo(repositoryName);
    }

    @GetMapping("/commit")
    @CrossOrigin
    public GithubCommit getCommitInfo(@RequestParam("repositoryName") final String repositoryName, @RequestParam("commitSha") final String commitSha) throws ExecutionException, InterruptedException {
        return githubService.getCommitInfo(repositoryName, commitSha);
    }

    @GetMapping("/commitComparison")
    @CrossOrigin
    public GithubCommitComparison getCommitComparison(@RequestParam("repositoryName") final String repositoryName, @RequestParam("baseCommitSha") final String baseCommitSha, @RequestParam("headCommitSha") final String headCommitSha) throws ExecutionException, InterruptedException {
        return githubService.getCommitComparison(repositoryName, baseCommitSha, headCommitSha);
    }
}
