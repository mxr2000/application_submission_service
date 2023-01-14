package edu.virginia.cs.application_submission.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    private int id;
    private Company company;
    private Season season;
    private String name;

    public Position(Company company, Season season, String name) {
        this.company = company;
        this.season = season;
        this.name = name;
    }
}
