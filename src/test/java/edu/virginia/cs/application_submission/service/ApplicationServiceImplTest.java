package edu.virginia.cs.application_submission.service;

import edu.virginia.cs.application_submission.exception.ApplicationDoesNotExistException;
import edu.virginia.cs.application_submission.exception.SeasonDoesNotExistException;
import edu.virginia.cs.application_submission.mapper.*;
import edu.virginia.cs.application_submission.pojo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApplicationServiceImplTest {
    @MockBean
    private PositionMapper positionMapper;
    @MockBean
    private CompanyMapper companyMapper;
    @MockBean
    private ApplicationMapper applicationMapper;
    @MockBean
    private SeasonMapper seasonMapper;

    @Autowired
    private ApplicationService applicationService;

    private LocalDate submissionDate;
    private Season season;

    @BeforeEach
    public void setUp() {
        submissionDate = LocalDate.parse("2022-08-05");
        season = new Season(1, "a");
    }

    @Test
    public void testCreateApplication_CompanyDoesNotExist() {
        when(seasonMapper.querySeasonById(1)).thenReturn(season);
        when(companyMapper.queryCompanyByName(anyString())).thenReturn(null);
        when(companyMapper.insertCompany(anyString(), anyString())).thenReturn(1);
        applicationService.createApplication(1, "a", "b", submissionDate);
        verify(companyMapper).insertCompany("a", "");
        verify(positionMapper).insertPosition(new Position(new Company("a", ""), season, "b"));
        verify(applicationMapper).insertApplication(new Application(0, submissionDate));
    }

    @Test
    public void testCreateApplication_SeasonDoesNotExist() {
        when(seasonMapper.querySeasonById(anyInt())).thenReturn(null);
        assertThrows(SeasonDoesNotExistException.class, () -> applicationService.createApplication(1, "a", "b", submissionDate));
        verify(companyMapper, never()).insertCompany(anyString(), anyString());
        verify(positionMapper, never()).insertPosition(any());
        verify(applicationMapper, never()).insertApplication(any());
    }

    @Test
    public void testCreateApplication_PositionDoesNotExist() {
        Company company = new Company("a", "");
        when(seasonMapper.querySeasonById(anyInt())).thenReturn(season);
        when(companyMapper.queryCompanyByName(anyString())).thenReturn(company);
        when(positionMapper.queryPositionBySeasonAndNameAndCompanyName("b", "a", 1)).thenReturn(null);
        applicationService.createApplication(1, "a", "b", submissionDate);
        verify(companyMapper, never()).insertCompany(anyString(), anyString());
        verify(positionMapper).insertPosition(new Position(company, season, "b"));
        verify(applicationMapper).insertApplication(any());
    }

    @Test
    public void testCreateApplication_PositionExists() {
        when(seasonMapper.querySeasonById(anyInt())).thenReturn(season);
        Company company = new Company("a", "");
        Position position = new Position(1, company, season, "SDE");
        when(companyMapper.queryCompanyByName(anyString())).thenReturn(company);
        when(positionMapper.queryPositionBySeasonAndNameAndCompanyName("SDE", "a", 1)).thenReturn(position);
        applicationService.createApplication(1, "a", "SDE", submissionDate);
        verify(companyMapper, never()).insertCompany(anyString(), anyString());
        verify(positionMapper, never()).insertPosition(any());
        verify(applicationMapper).insertApplication(any());
    }

    @Test
    public void testUpdateApplicationStatus_ApplicationDoesNotExist() {
        when(applicationMapper.updateStatus(anyInt(), any(), any())).thenReturn(0);
        assertThrows(ApplicationDoesNotExistException.class, () -> applicationService.updateApplicationStatus(1, submissionDate, ApplicationStatus.rejected));
    }

    @Test
    public void testUpdateApplicationStatus_ApplicationExists() {
        when(applicationMapper.updateStatus(anyInt(), any(), any())).thenReturn(1);
        Application application = new Application();
        application.setId(1);
        when(applicationMapper.queryApplicationById(anyInt())).thenReturn(application);
        applicationService.updateApplicationStatus(0, submissionDate, ApplicationStatus.rejected);
        verify(applicationMapper).updateStatus(0, submissionDate, ApplicationStatus.rejected);
    }


}
