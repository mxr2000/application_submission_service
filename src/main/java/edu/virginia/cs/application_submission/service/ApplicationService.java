package edu.virginia.cs.application_submission.service;

import edu.virginia.cs.application_submission.exception.ApplicationDoesNotExistException;
import edu.virginia.cs.application_submission.exception.SeasonDoesNotExistException;
import edu.virginia.cs.application_submission.pojo.Application;
import edu.virginia.cs.application_submission.pojo.ApplicationStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ApplicationService {
    /**
     * create an application for a position
     * if company does not exist, create the company
     * if position does not exist, create the position
     * @throws SeasonDoesNotExistException if season does not exist
     */
    int createApplication(int seasonId, String companyName, String positionName, LocalDate submissionDate) throws SeasonDoesNotExistException;

    List<Application> getAllApplications();

    void updateApplicationStatus(int id, LocalDate updateTime, ApplicationStatus status) throws ApplicationDoesNotExistException;

    Application getApplicationById(int id);
}
