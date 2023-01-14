package edu.virginia.cs.application_submission.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Season {
    private int id;
    private String name;

    public Season(String name) {
        this.name = name;
    }

    public Season(int id) {
        this.id = id;
    }
}
