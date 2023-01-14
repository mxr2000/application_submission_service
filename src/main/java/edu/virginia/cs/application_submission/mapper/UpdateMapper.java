package edu.virginia.cs.application_submission.mapper;

import edu.virginia.cs.application_submission.pojo.Update;
import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface UpdateMapper {
    void insertUpdate(int applicationId, Update update);
    List<Update> queryUpdatesByApplicationId(int id);

    @Flush
    int updateCompleteTime(int id, LocalDate completeTime);
}
