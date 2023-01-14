package edu.virginia.cs.application_submission.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    private int id;
    private Position position;
    private LocalDate submissionDate;
    private LocalDate updateDate;
    private ApplicationStatus status;
    private List<Update> updates;

    public Application(int positionId, LocalDate submissionDate) {
        this.position = new Position();
        position.setId(positionId);
        this.submissionDate = submissionDate;
    }
}
