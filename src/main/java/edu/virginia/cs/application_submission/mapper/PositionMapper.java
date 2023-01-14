package edu.virginia.cs.application_submission.mapper;

import edu.virginia.cs.application_submission.exception.CompanyDoesNotExistException;
import edu.virginia.cs.application_submission.pojo.Position;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PositionMapper {
    int insertPosition(Position position);
    Position queryPositionById(int id);
    Position queryPositionBySeasonAndNameAndCompanyName(String name, String companyName, int seasonId);
}
