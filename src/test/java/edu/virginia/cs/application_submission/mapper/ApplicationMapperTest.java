package edu.virginia.cs.application_submission.mapper;

import edu.virginia.cs.application_submission.pojo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
public class ApplicationMapperTest {
    @Autowired
    private ApplicationMapper applicationMapper;
    private Position presetPosition;
    private Update presetUpdate;
    private Application existentApplication;
    private LocalDate presetDate;

    @BeforeEach
    public void setUp() {
        Company company = new Company("TikTok", "");
        Season season = new Season(1, "2023 Fall Intern");
        LocalDate updateDate = LocalDate.parse("2022-08-15");
        presetUpdate = new Update(1, LocalDate.parse("2022-08-15"), LocalDate.parse("2022-08-20"), UpdateType.oa, "easy");
        presetPosition = new Position(1, company, season, "SDE");
        presetDate = LocalDate.parse("2000-08-05");
        existentApplication = new Application(1, presetPosition, presetDate, updateDate, ApplicationStatus.submitted, List.of(presetUpdate));
    }

    @Test
    public void testInsertApplication_PositionDoesNotExist() {
        assertThrows(DataIntegrityViolationException.class, () -> applicationMapper.insertApplication(new Application(100, presetDate)));
    }

    @Test
    public void testInsertApplication_Success() {
        Application application = new Application(1, presetDate);
        applicationMapper.insertApplication(application);
        Application expected = new Application(application.getId(), presetPosition, presetDate, presetDate, ApplicationStatus.submitted, List.of());
        assertEquals(expected, applicationMapper.queryApplicationById(application.getId()));
    }

    @Test
    @Order(1)
    public void testQueryAllApplications() {
        assertEquals(List.of(existentApplication), applicationMapper.queryAllApplications());
    }

    @Test
    public void testQueryApplicationById() {
        assertEquals(existentApplication, applicationMapper.queryApplicationById(1));
    }

    @Test
    public void testUpdateStatus_ApplicationDoesNotExist() {
        assertEquals(0, applicationMapper.updateStatus(2, presetDate, ApplicationStatus.rejected));
    }

    @Test
    public void testUpdateStatus_Success() {
        Application application = new Application(1, presetPosition, presetDate, presetDate, ApplicationStatus.rejected, List.of(presetUpdate));
        assertEquals(1, applicationMapper.updateStatus(1, presetDate, ApplicationStatus.rejected));
        assertEquals(application, applicationMapper.queryApplicationById(1));
    }
}
