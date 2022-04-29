package pl.edu.uj.ii.main.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uj.ii.main.models.GitRequest;
import pl.edu.uj.ii.main.models.RepositoryResult;
import pl.edu.uj.ii.main.services.GitService;

import java.util.List;

@RestController
@RequestMapping("/repositories")
@AllArgsConstructor
public class GitController {

    private final GitService gitService;

    @PostMapping
    @CrossOrigin
    public List<RepositoryResult> getCommits(@RequestBody final GitRequest repositoryRequest) {
        final String repoUrl = repositoryRequest.getRepoUrl();
        return gitService.getRepository(repoUrl);
    }
}
