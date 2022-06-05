package pl.edu.uj.ii.main.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uj.ii.main.models.CommitResult;
import pl.edu.uj.ii.main.services.GithubService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/github")
@AllArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping
    @CrossOrigin
    public List<CommitResult> getCommits(@RequestParam("name") final String name) throws IOException {
        return githubService.getRepository(name);
    }
}
