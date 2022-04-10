package pl.edu.uj.ii.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uj.ii.main.models.RepositoryRequest;

@RestController
@RequestMapping("/repositories")
public class GitRepositoryController {
    @GetMapping
    public String getCommits(@RequestBody final RepositoryRequest repositoryRequest) {
        return repositoryRequest.getRepoUrl();
    }
}
