package pl.edu.uj.ii.main.models;

import lombok.Data;

@Data
public class CommitChangesAmount {
    private final Integer linesAdded;
    private final Integer linesChanged;
    private final Integer linesDeleted;
}
