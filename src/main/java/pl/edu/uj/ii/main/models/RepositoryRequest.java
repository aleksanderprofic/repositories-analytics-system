package pl.edu.uj.ii.main.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepositoryRequest {
    private final String repoUrl;

    public RepositoryRequest(@JsonProperty("url") String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getRepoUrl() {
        return repoUrl;
    }
}
