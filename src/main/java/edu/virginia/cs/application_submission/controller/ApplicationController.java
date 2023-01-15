package edu.virginia.cs.application_submission.controller;

import edu.virginia.cs.application_submission.pojo.Application;
import edu.virginia.cs.application_submission.pojo.ApplicationStatus;
import edu.virginia.cs.application_submission.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<Application> createApplication(@Valid @RequestBody CreateApplicationParams params) {
        int id = applicationService.createApplication(params.seasonId, params.companyName, params.positionName, params.submissionDate);
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @PutMapping
    public ResponseEntity<String> updateStatus(@Valid @RequestBody UpdateStatusParams params) {
        System.out.println(params);
        System.out.println(TimeZone.getDefault());
        applicationService.updateApplicationStatus(params.applicationId, params.updateDate, params.status);
        return ResponseEntity.ok("success");
    }


    public record CreateApplicationParams(
            @NotNull
            int seasonId,
            @NotBlank
            String companyName,
            @NotBlank
            String positionName,
            @DateTimeFormat()
            LocalDate submissionDate
    ) {
    }

    public record UpdateStatusParams(
            @NotNull
            int applicationId,
            @NotNull
            ApplicationStatus status,
            @DateTimeFormat()
            LocalDate updateDate
    ) {
    }
}
