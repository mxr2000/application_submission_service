package edu.virginia.cs.application_submission.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Update {
    private int id;
    private LocalDate notifyTime;
    private LocalDate completeTime;
    private UpdateType type;
    private String spec;

    public Update(LocalDate notifyTime, UpdateType type) {
        this.notifyTime = notifyTime;
        this.type = type;
    }

    public Update(LocalDate notifyTime, UpdateType type, String spec) {
        this.notifyTime = notifyTime;
        this.type = type;
        this.spec = spec;
    }
}
