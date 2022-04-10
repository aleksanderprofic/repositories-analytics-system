package pl.edu.uj.ii.main.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GitRequest {
    private final String repoUrl;

    public GitRequest(@JsonProperty("url") String repoUrl) {
        this.repoUrl = repoUrl;
    }
}
