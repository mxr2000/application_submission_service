package edu.virginia.cs.application_submission.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private String name;
    private String spec;

    public Company(String name) {
        this.name = name;
    }
}
