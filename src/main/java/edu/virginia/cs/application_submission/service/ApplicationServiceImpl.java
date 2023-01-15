package edu.virginia.cs.application_submission.service;

import edu.virginia.cs.application_submission.exception.ApplicationDoesNotExistException;
import edu.virginia.cs.application_submission.exception.SeasonDoesNotExistException;
import edu.virginia.cs.application_submission.mapper.*;
import edu.virginia.cs.application_submission.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    public ApplicationServiceImpl(ApplicationMapper applicationMapper, CompanyMapper companyMapper, PositionMapper positionMapper, SeasonMapper seasonMapper) {
        this.applicationMapper = applicationMapper;
        this.companyMapper = companyMapper;
        this.positionMapper = positionMapper;
        this.seasonMapper = seasonMapper;
    }

    private final ApplicationMapper applicationMapper;
    private final CompanyMapper companyMapper;
    private final PositionMapper positionMapper;
    private final SeasonMapper seasonMapper;

    @Override
    public int createApplication(int seasonId, String companyName, String positionName, LocalDate submissionDate) throws SeasonDoesNotExistException {
        Season season = seasonMapper.querySeasonById(seasonId);
        if (season == null) {
            throw new SeasonDoesNotExistException();
        }
        Company company = companyMapper.queryCompanyByName(companyName);
        if (company == null) {
            companyMapper.insertCompany(companyName, "");
            company = new Company(companyName, "");
        }
        Position position = positionMapper.queryPositionBySeasonAndNameAndCompanyName(positionName, companyName, seasonId);
        if (position == null) {
            position = new Position(company, season, positionName);
            positionMapper.insertPosition(position);
        }
        Application application = new Application(position.getId(), submissionDate);
        applicationMapper.insertApplication(application);
        return application.getId();
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationMapper.queryAllApplications();
    }

    @Override
    public void updateApplicationStatus(int id, LocalDate updateTime, ApplicationStatus status) throws ApplicationDoesNotExistException {
        if (applicationMapper.updateStatus(id, updateTime, status) != 1) {
            throw new ApplicationDoesNotExistException();
        }
    }

    @Override
    public Application getApplicationById(int id) {
        return applicationMapper.queryApplicationById(id);
    }


}
