package pl.edu.uj.ii.main.models;

import lombok.Data;

import java.util.List;

@Data
public class Branch {
    private final String name;
    private final List<Commit> commits;
}
