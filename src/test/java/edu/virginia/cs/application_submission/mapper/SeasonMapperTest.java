package edu.virginia.cs.application_submission.mapper;

import edu.virginia.cs.application_submission.pojo.Season;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
public class SeasonMapperTest {
    @Autowired
    private SeasonMapper seasonMapper;

    @Test
    public void testInsertSeason() {
        Season season = new Season("a");
        seasonMapper.insertSeason(season);
        assertEquals(season, seasonMapper.querySeasonById(season.getId()));
    }

    @Test
    public void testQuerySeasonById_Found() {
        Season season = new Season(1, "2023 Fall Intern");
        assertEquals(season, seasonMapper.querySeasonById(1));
    }

    @Test
    public void testQuerySeasonById_NotFound() {
        assertNull(seasonMapper.querySeasonById(100));
    }
}
