package edu.virginia.cs.application_submission.service;

import edu.virginia.cs.application_submission.exception.ApplicationDoesNotExistException;
import edu.virginia.cs.application_submission.exception.UpdateNoEffectException;
import edu.virginia.cs.application_submission.mapper.UpdateMapper;
import edu.virginia.cs.application_submission.pojo.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    public UpdateServiceImpl(UpdateMapper updateMapper) {
        this.updateMapper = updateMapper;
    }

    private final UpdateMapper updateMapper;


    @Override
    public void createUpdate(int applicationId, Update update) throws ApplicationDoesNotExistException {
        try {
            updateMapper.insertUpdate(applicationId, update);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationDoesNotExistException();
        }
    }

    @Override
    public void updateUpdateCompleteTime(int id, LocalDate completeTime) throws UpdateNoEffectException {
        int cnt = updateMapper.updateCompleteTime(id, completeTime);
        System.out.println("cnt = " + cnt);
        if (cnt == 0) {
            throw new UpdateNoEffectException();
        }
    }
}
