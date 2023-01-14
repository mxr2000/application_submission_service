package edu.virginia.cs.application_submission.service;

import edu.virginia.cs.application_submission.exception.ApplicationDoesNotExistException;
import edu.virginia.cs.application_submission.exception.UpdateNoEffectException;
import edu.virginia.cs.application_submission.pojo.Update;

import java.time.LocalDate;

public interface UpdateService {
    /**
     * @throws ApplicationDoesNotExistException if application does not exist
     */
    void createUpdate(int applicationId, Update update) throws ApplicationDoesNotExistException;

    /**
     * @throws UpdateNoEffectException if update does not exist
     */
    void updateUpdateCompleteTime(int id, LocalDate completeTime) throws UpdateNoEffectException;
}
