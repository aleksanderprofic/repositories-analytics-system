package pl.edu.uj.ii.main.models;

import lombok.Data;

import java.util.List;

@Data
public class Commit {
    private final String message;
    private final long time;
    private final String sha;
    private final List<String> parents;
}
