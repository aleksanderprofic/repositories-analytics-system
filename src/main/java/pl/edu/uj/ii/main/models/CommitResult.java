package pl.edu.uj.ii.main.models;

import lombok.Data;

import java.util.List;

@Data
public class CommitResult {
    private final String message;
    private final int time;
    private final String sha;
    private final List<String> parents;
}
