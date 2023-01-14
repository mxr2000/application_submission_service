package edu.virginia.cs.application_submission.mapper;

import edu.virginia.cs.application_submission.pojo.Application;
import edu.virginia.cs.application_submission.pojo.ApplicationStatus;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Mapper
public interface ApplicationMapper {
    Application queryApplicationById(int id);
    List<Application> queryAllApplications();
    void insertApplication(Application application);
    int updateStatus(int id, LocalDate updateTime, ApplicationStatus status);
}
