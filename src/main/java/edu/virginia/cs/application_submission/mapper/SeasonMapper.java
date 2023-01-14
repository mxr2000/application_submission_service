package edu.virginia.cs.application_submission.mapper;

import edu.virginia.cs.application_submission.pojo.Season;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeasonMapper {
    void insertSeason(Season season);
    Season querySeasonById(int id);
}
