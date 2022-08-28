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
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/github")
@AllArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/branches")
    @CrossOrigin
    public List<Branch> getBranches(@RequestParam("repositoryName") final String repositoryName,
                                    @RequestParam(value = "since", required = false) String dateSince,
                                    @RequestParam(value = "until", required = false) String dateUntil) throws IOException, ParseException {
        return githubService.getCommits(repositoryName, dateSince, dateUntil);
    }

    @GetMapping("/getCommitChangesAmount")
    public List<CommitChangesAmount> getCommitChangesAmount(@RequestParam("name") final String name) throws IOException {
        return githubService.getCommitChangesAmount(name);
    }
}
