package pl.edu.uj.ii.main.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uj.ii.main.models.Branch;
import pl.edu.uj.ii.main.models.CommitChangesAmount;
import pl.edu.uj.ii.main.services.GithubService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/github")
@AllArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/branches")
    @CrossOrigin
    public List<Branch> getBranches(@RequestParam("repositoryName") final String repositoryName) throws IOException {
        return githubService.getCommits(repositoryName);
    }

    @GetMapping("/getCommitChangesAmount")
    public List<CommitChangesAmount> getCommitChangesAmount(@RequestParam("name") final String name) throws IOException {
        return githubService.getCommitChangesAmount(name);
    }
}
