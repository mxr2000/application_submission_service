package edu.virginia.cs.application_submission.controller;

import edu.virginia.cs.application_submission.pojo.Update;
import edu.virginia.cs.application_submission.pojo.UpdateType;
import edu.virginia.cs.application_submission.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RestController
@RequestMapping("/update")
public class UpdateController {

    @Autowired
    private UpdateService updateService;

    @PostMapping
    public ResponseEntity<Update> createUpdate(@Valid @RequestBody CreateUpdateParams params) {
        Update update = new Update(params.notifyTime, params.type);
        updateService.createUpdate(params.applicationId, update);
        return ResponseEntity.ok(update);
    }

    @PutMapping("complete_time")
    public ResponseEntity<String> updateCompleteTime(@Valid @RequestBody UpdateCompleteTimeParams params) {
        updateService.updateUpdateCompleteTime(params.updateId, params.time);
        return ResponseEntity.ok("success");
    }

    public record CreateUpdateParams(
            @NotNull
            int applicationId,
            @NotNull
            UpdateType type,
            @DateTimeFormat()
            LocalDate notifyTime,
            String spec
    ) {
    }

    public record UpdateCompleteTimeParams(
            @NotNull
            int updateId,
            @DateTimeFormat()
            LocalDate time
    ) {
    }
}
